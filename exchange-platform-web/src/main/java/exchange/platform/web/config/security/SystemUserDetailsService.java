package exchange.platform.web.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import exchange.platform.web.domain.SystemUser;
import exchange.platform.web.service.UserService;

public class SystemUserDetailsService implements UserDetailsService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;

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
        }
		
		User user = new User(username, systemUser.getPassword(),
				AuthorityUtils.commaSeparatedStringToAuthorityList(systemUser.getRole()));

		return user;
	}
}