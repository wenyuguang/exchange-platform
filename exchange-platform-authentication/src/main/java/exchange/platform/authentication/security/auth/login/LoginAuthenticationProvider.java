package exchange.platform.authentication.security.auth.login;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;

import exchange.platform.authentication.domain.UserInfo;
import exchange.platform.authentication.domain.UserRole;
import exchange.platform.authentication.security.model.UserContext;
import exchange.platform.authentication.service.UserInfoService;
import exchange.platform.authentication.service.UserRoleService;


/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午4:36:44</p>
 * @author Tony
 * @version 1.0
 *
 */
@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {
	
	private Logger logger = LoggerFactory.getLogger(LoginAuthenticationProvider.class);
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private UserInfoService userService;
	@Autowired
	private UserRoleService roleService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");
        logger.debug("[authentication info] - [{}]", JSON.toJSONString(authentication));
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        UserInfo user = userService.findUserByUserName(username);
        if(user == null) throw new UsernameNotFoundException("User not found: " + username);
        
//        if (!StringUtils.equals(password, user.getPassword())) {
        if(!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }
        List<UserRole> roles = roleService.getRoleByUser(user);
        if (roles == null || roles.size() <= 0) throw new InsufficientAuthenticationException("User has no roles assigned");
        
        List<GrantedAuthority> authorities = roles.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.authority()))
                .collect(Collectors.toList());
        
        UserContext userContext = UserContext.create(user.getUserName(), authorities);
        
        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
