package exchange.platform.gateway;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value="service-test")
public interface TestService{

	@RequestMapping("/test")
	@ResponseBody
	public String test();
}
