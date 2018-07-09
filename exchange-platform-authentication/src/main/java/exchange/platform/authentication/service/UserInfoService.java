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

	
	public UserInfo findUserByUserName(String username);
    
}
