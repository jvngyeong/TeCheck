package DDL.stock;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.DatagramAcceptor;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
import org.springframework.stereotype.Service;

import DDL.vo.StockA3;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
@Service
public class MultiThreadedUDPServer {
    private static final MultiThreadedUDPServer instance = new MultiThreadedUDPServer();
    private KafkaProducer<String, String> producer;
    DatagramAcceptor acceptor;
    public static MultiThreadedUDPServer getInstance() {
        return instance;
    }
    private MultiThreadedUDPServer() {
        // Kafka Producer 초기화
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        this.producer = new KafkaProducer<>(props);
    }
    @PostConstruct
    public void startServer() {
        acceptor = new NioDatagramAcceptor();
        acceptor.setHandler(new UDPHandler(producer));
        DatagramSessionConfig dcfg = acceptor.getSessionConfig();
        dcfg.setReuseAddress(true);
        try {
            acceptor.bind(new InetSocketAddress(22101));
            System.out.println("MultiThreaded UDP Echo Server is listening on port 22101");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @PreDestroy
    public void stopServer() {
    	if(acceptor != null && acceptor.isActive()) {
    		System.out.println("주식 데이터 서버의 자원 및 포트를 정리했습니다.");
    		acceptor.unbind();
    		acceptor.dispose();
    	}
    }
    private static class UDPHandler extends IoHandlerAdapter {
        private final ExecutorService threadPool = Executors.newCachedThreadPool();
        private KafkaProducer<String, String> producer;
        public UDPHandler(KafkaProducer<String, String> producer) {
            this.producer = producer;
        }
        @Override
        public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
            cause.printStackTrace();
        }
        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {
            if (message instanceof IoBuffer) {
                IoBuffer buffer = (IoBuffer) message;
                threadPool.execute(new ClientHandler(session, buffer,producer));
            }
        }
    }
    private static class ClientHandler implements Runnable {
        private IoSession session;
        private IoBuffer buffer;
        private StockA3 stockA3;
        private long startTime;
        private long endTime;
        private KafkaProducer<String, String> producer;
        public ClientHandler(IoSession session, IoBuffer message, KafkaProducer<String, String> producer) {
            this.session = session;
            this.buffer = message.duplicate(); // IoBuffer 복제
            this.stockA3 = new StockA3(); // StockA3 객체 초기화
            this.producer = producer;
        }
        public void run() {
            try {
                long threadCnt = Thread.activeCount();
                long threadId = Thread.currentThread().getId();
                if (buffer.remaining() < 195) {
                    String bufferContent = buffer.getString(buffer.remaining(), StandardCharsets.UTF_8.newDecoder());
                    return;
                }
                byte[] data = new byte[buffer.remaining()]; // 남은 데이터 크기만큼 배열 생성
                buffer.get(data); // 버퍼 내용을 배열에 복사
                // 받은 메세지를 스레드가 코드별로 분류해서 처리
                StockA3 transaction = stockA3.parseTransaction(buffer);
                // 여기서부터는 transaction 객체를 이용하여 원하는 작업 수행
                String logMessage = "[" + threadCnt +"]" +  " T_id = " + threadId + ":" + "거래시간 = " + transaction.get거래시간() + ":" + "종목코드 = " + transaction.get종목코드() + ":" +
                            "거래종류 = " + transaction.get거래종류() + ":" +
                            "체결가격 = " + transaction.get체결가격() + ":" +
                            "거래량 = " + transaction.get거래량() + ":" + "누적거래량 = " + transaction.get누적거래량();
                producer.send(new ProducerRecord<>("stock", Long.toString(threadId), logMessage));  // Kafka에 메시지 보내기
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}