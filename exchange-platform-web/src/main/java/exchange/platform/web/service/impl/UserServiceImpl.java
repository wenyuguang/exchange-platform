package exchange.platform.web.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import exchange.platform.web.domain.SystemUser;
import exchange.platform.web.mapper.UserMapper;
import exchange.platform.web.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public SystemUser findUserByUserName(String username) throws Exception {
//		SystemUser systemUser = new SystemUser();
//		systemUser.setId(1);
//		systemUser.setUsername("admin");
//		systemUser.setPassword("admin");
//		systemUser.setRole("ADMIN");
		
		SystemUser systemUser = userMapper.findUserByUserName(username);
		
		return systemUser;
	}

	@Override
	public String addUser(String userBase) throws Exception {
		//格式：username:password:role
		String userInfo[] = new String(Base64Utils.decodeFromString(userBase),"UTF-8").split(":");
		Map<String, String> map = new HashMap<>();
		map.put("username", userInfo[0]);
		map.put("password", passwordEncoder.encode(userInfo[1]));
		map.put("role",     userInfo[2]);
		userMapper.addUser(map);
		return null;
	}
}