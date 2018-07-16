package exchange.platform.authentication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exchange.platform.authentication.domain.UserInfo;
import exchange.platform.authentication.mapper.UserInfoMapper;
import exchange.platform.authentication.service.UserInfoService;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月11日 上午11:57:54</p>
 * @author Tony
 * @version 1.0
 *
 */
@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	public UserInfo findUserByUserName(String username) {
		
		UserInfo UserInfo = userInfoMapper.findUserByUserName(username);
        return UserInfo;
    }

	@Override
	public UserInfo findUserByUserNameAndServiceName(String username, String serviceName) {
		UserInfo UserInfo = userInfoMapper.findUserByUserNameAndServiceName(username, serviceName);
        return UserInfo;
	}
}
