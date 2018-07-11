package exchange.platform.authentication.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import exchange.platform.authentication.domain.UserInfo;
import exchange.platform.authentication.domain.UserRole;
import exchange.platform.authentication.service.UserRoleService;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月11日 上午11:57:59</p>
 * @author Tony
 * @version 1.0
 *
 */
@Service
public class UserRoleServiceImpl implements UserRoleService{

	@Override
	public List<UserRole> getRoleByUser(UserInfo user) {
        if ("admin".equals(user.getUserName())) {
            //@see ExpressionUrlAuthorizationConfigurer  private static String hasAnyRole(String... authorities) 带 ROLE_
            return Lists.newArrayList(new UserRole("ROLE_ADMIN"));
        }
        return null;
    }

	
}
