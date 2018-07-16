package exchange.platform.authentication.service;

import exchange.platform.authentication.domain.UserInfo;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午4:34:12</p>
 * @author Tony
 * @version 1.0
 *
 */
public interface UserInfoService {

	/**
	 * 
	 * @function 
	 * @author Tony
	 * @creaetime 2018年7月16日 下午4:46:51
	 * @param username    用户名
	 * @return
	 */
	public UserInfo findUserByUserName(String username);
	/**
	 * 
	 * @function 
	 * @author Tony
	 * @creaetime 2018年7月16日 下午4:46:51
	 * @param username    用户名
	 * @param serviceName 服务名
	 * @return
	 */
	public UserInfo findUserByUserNameAndServiceName(String username, String serviceName);
    
}
