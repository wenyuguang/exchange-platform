package exchange.platform.authentication.service;

import java.util.List;

import exchange.platform.authentication.domain.UserInfo;
import exchange.platform.authentication.domain.UserRole;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午4:19:20</p>
 * @author Tony
 * @version 1.0
 *
 */
public interface UserRoleService {

	public List<UserRole> getRoleByUser(UserInfo user);
}
