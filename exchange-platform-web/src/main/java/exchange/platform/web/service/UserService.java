package exchange.platform.web.service;

import exchange.platform.web.domain.SystemUser;

public interface UserService {
	
	public SystemUser findUserByUserName(String paramString) throws Exception;
	/**
	 * 
	 * @function 添加用户
	 * @author Tony
	 * @creaetime 2018年7月5日 下午3:32:04
	 * @param userBase
	 * @return
	 * @throws Exception
	 */
	public String addUser(String userBase) throws Exception;
}
