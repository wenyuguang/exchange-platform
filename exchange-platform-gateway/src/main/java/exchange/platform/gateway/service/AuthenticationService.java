package exchange.platform.gateway.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value="service-authentication")
public interface AuthenticationService{

	/**
	 * 
	 * @function 登录申请token串
	 * @author Tony
	 * @creaetime 2018年7月2日 下午5:17:03
	 * @param authentication 账号密码base64串
	 * @return 返回包含token的json串
	 */
	@RequestMapping("/get_token")
	@ResponseBody
	public String getToken(String authentication);
	/**
	 * 
	 * @function 验证鉴权
	 * @author Tony
	 * @creaetime 2018年7月2日 下午5:17:51
	 * @param token token串
	 * @return
	 */
	@RequestMapping("/auth_token")
	@ResponseBody
	public boolean authenticateToken(String token);
}
