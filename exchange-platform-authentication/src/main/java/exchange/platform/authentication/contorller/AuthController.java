package exchange.platform.authentication.contorller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import exchange.platform.authentication.service.AuthService;
import exchange.platform.authentication.util.RequestUtils;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午5:35:31</p>
 * @author Tony
 * @version 1.0
 *
 */
@Controller
public class AuthController {

    @Autowired
    private AuthService     authService;


    @GetMapping("/test1")
    public String test1() {
        return "test1";
    }

    @GetMapping("/auth/test2")
    public String test2() {
        return "test2";
    }

    /**
     * 
     * @function "/auth/refresh_token"
     * @author Tony
     * @creaetime 2018年7月9日 下午5:39:26
     * @param request
     * @return
     */
    @GetMapping(RequestUtils.TOKEN_REFRESH_ENTRY_POINT)
    @ResponseBody
    public Object refreshToken(HttpServletRequest request) {
    	return authService.refreshToken(request);
    }
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
    public boolean verify(@RequestParam("token")String tokens) {
    	return authService.verify(tokens);
    }
    /**
     * 
     * @function "/login"
     * @author Tony
     * @creaetime 2018年7月11日22:48:52
     * @param request
     * @return
     */
    @PostMapping(RequestUtils.TOKEN_BASED_LOGIN_ENTRY_POINT)
    public Object login() {
    	return "forward:/auth/login";
    }
}
