package DDL;

import java.net.InetSocketAddress;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChattingConfig {
	@Bean
    public InetSocketAddress chattingAddress() {
        //return new InetSocketAddress("172.16.105.174", 2020);
		return new InetSocketAddress("localhost", 2020);
	}
}
   