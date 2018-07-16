package exchange.platform.authentication.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import exchange.platform.authentication.domain.UserInfo;

@Mapper
public interface UserInfoMapper {

	public UserInfo findUserByUserName(String username);

	public UserInfo findUserByUserNameAndServiceName(@Param("username") String username,@Param("serviceName") String serviceName);

}
