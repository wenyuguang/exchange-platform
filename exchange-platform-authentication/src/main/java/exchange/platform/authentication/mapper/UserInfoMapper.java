package exchange.platform.authentication.mapper;

import org.apache.ibatis.annotations.Mapper;

import exchange.platform.authentication.domain.UserInfo;

@Mapper
public interface UserInfoMapper {

	public UserInfo findUserByUserName(String username);

}
