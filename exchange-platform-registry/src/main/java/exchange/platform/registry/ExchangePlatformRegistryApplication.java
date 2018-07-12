package exchange.platform.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月12日 上午9:36:06</p>
 * @author Tony
 * @version 1.0
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class ExchangePlatformRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangePlatformRegistryApplication.class, args);
	}
}
