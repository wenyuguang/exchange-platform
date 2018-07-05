package exchange.platform.web.service.impl;

import org.springframework.stereotype.Service;

import exchange.platform.web.domain.SystemUser;
import exchange.platform.web.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	public SystemUser findUserByUserName(String username) throws Exception {
		SystemUser systemUser = new SystemUser();
		systemUser.setId(1);
		systemUser.setUsername("admin");
		systemUser.setPassword("admin");
		systemUser.setRole("admin");
		return systemUser;
	}
}