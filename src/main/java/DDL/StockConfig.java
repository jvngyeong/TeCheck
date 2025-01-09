package DDL;

import java.net.InetSocketAddress;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockConfig {
	@Bean
    public InetSocketAddress stockAddress() {
        return new InetSocketAddress("172.16.105.174", 10120);
    }
}
