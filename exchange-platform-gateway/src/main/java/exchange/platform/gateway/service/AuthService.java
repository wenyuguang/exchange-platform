package exchange.platform.gateway.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import exchange.platform.common.auth.RequestUtils;
import exchange.platform.common.code.ServiceResponse;
import exchange.platform.common.http.HttpStatus;
import feign.hystrix.FallbackFactory;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午5:35:31</p>
 * @author Tony
 * @version 1.0
 *
 */
@FeignClient(value="service-authen", fallback = AuthServiceHystrix.class)
public interface AuthService {
	
	@GetMapping(RequestUtils.TOKEN_REFRESH_ENTRY_POINT)
	@ResponseBody
    public Object refreshToken(HttpServletRequest request);
    /**
     * 
     * @function "/auth/verify"
     * @author Tony
     * @creaetime 2018年7月11日21:45:54
     * @param request
     * @return
     */
    @GetMapping(RequestUtils.TOKEN_VERIFY_ENTRY_POINT)
    @ResponseBody
    public boolean verify(@RequestParam("token")String tokens);
    
    /**
     * 
     * @function "/login"
     * @author Tony
     * @creaetime 2018年7月11日22:48:52
     * @param request
     * @return
     */
    @PostMapping(RequestUtils.TOKEN_BASED_LOGIN_ENTRY_POINT)
    public Object login();
}
@Component
class AuthServiceHystrix implements AuthService{

	private static Logger logger = LoggerFactory.getLogger(AuthService.class);
	
	@Override
	public Object refreshToken(HttpServletRequest request) {
		logger.error("refreshToken error~!!!");
		return new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server error~~!!!");
	}

	@Override
	public boolean verify(@RequestParam("token")String tokens) {
		logger.error("verifyToken error~!!!");
		return false;
	}

	@Override
	public Object login() {
		logger.error("refreshToken error~!!!");
		return new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server error~~!!!");
	}
}
//FallbackFactory使用示例
//@Component
class Fallback<T> implements FallbackFactory<ServiceResponse> {

	@Override
	public ServiceResponse create(Throwable cause) {
		// TODO Auto-generated method stub
		return null;
	}
}
