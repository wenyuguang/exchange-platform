package exchange.platform.authentication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import exchange.platform.authentication.service.UserInfoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExchangePlatformAuthenticationApplicationTests {

	@Autowired
	private UserInfoService userInfoService;
	
	@Test
	public void contextLoads() {
		String pd = new BCryptPasswordEncoder().encode("admin");
		System.out.println(pd);
		System.out.println(userInfoService.findUserByUserName("admin").toString());
	}

}
