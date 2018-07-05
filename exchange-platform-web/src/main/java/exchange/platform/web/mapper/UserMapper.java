package exchange.platform.web.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import exchange.platform.web.domain.SystemUser;

@Mapper
public interface UserMapper {

	void addUser(Map<String, String> map);

	SystemUser findUserByUserName(String username);

}
