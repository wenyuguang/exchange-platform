package exchange.platform.authentication.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticationController {

	@RequestMapping("/authentication")
	public Object authentication() {
		return "/login";
	}
}
