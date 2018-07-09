package exchange.platform.authentication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exchange.platform.authentication.domain.UserInfo;
import exchange.platform.authentication.mapper.UserInfoMapper;
import exchange.platform.authentication.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	public UserInfo findUserByUserName(String username) {
		
		UserInfo UserInfo = userInfoMapper.findUserByUserName(username);
        return UserInfo;
    }
}
