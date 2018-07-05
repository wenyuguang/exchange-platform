package exchange.platform.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exchange.platform.web.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/addUser")
	public String addUser(String userBase) throws Exception {
		
		return userService.addUser(userBase);
	}
}
