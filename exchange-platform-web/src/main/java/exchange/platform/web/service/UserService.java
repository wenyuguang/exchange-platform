package exchange.platform.web.service;

import exchange.platform.web.domain.SystemUser;

public interface UserService {
	
	public SystemUser findUserByUserName(String paramString) throws Exception;
}
