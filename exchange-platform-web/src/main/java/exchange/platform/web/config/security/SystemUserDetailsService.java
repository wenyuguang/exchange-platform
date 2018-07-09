package exchange.platform.web.config.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import exchange.platform.web.domain.Permission;
import exchange.platform.web.domain.SystemUser;
import exchange.platform.web.mapper.PermissionMapper;
import exchange.platform.web.service.UserService;

public class SystemUserDetailsService implements UserDetailsService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	@Autowired
	private PermissionMapper permissionMapper;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		this.logger.info("用户的用户名: {}", username);

		SystemUser systemUser = null;
		try {
			systemUser = this.userService.findUserByUserName(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if( systemUser == null ){
            throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
        }else {
        	List<Permission> permissions = permissionMapper.findByAdminUserId(systemUser.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList <>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getName()!=null) {
                	GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                	//1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                	grantedAuthorities.add(grantedAuthority);
                }
            }
            return new User(systemUser.getUsername(), systemUser.getPassword(), grantedAuthorities);
        }
		
//		User user = new User(username, systemUser.getPassword(),
//				AuthorityUtils.commaSeparatedStringToAuthorityList(""));
//
//		return user;
	}
}