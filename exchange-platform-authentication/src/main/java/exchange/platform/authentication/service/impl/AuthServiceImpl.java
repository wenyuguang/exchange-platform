package exchange.platform.authentication.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import exchange.platform.authentication.domain.UserInfo;
import exchange.platform.authentication.domain.UserRole;
import exchange.platform.authentication.security.auth.token.extractor.TokenExtractor;
import exchange.platform.authentication.security.config.TokenProperties;
import exchange.platform.authentication.security.exceptions.InvalidTokenException;
import exchange.platform.authentication.security.model.UserContext;
import exchange.platform.authentication.security.model.token.RawAccessToken;
import exchange.platform.authentication.security.model.token.RefreshToken;
import exchange.platform.authentication.security.model.token.TokenFactory;
import exchange.platform.authentication.service.AuthService;
import exchange.platform.authentication.service.UserInfoService;
import exchange.platform.authentication.service.UserRoleService;
import exchange.platform.authentication.util.RequestUtils;
import exchange.platform.common.code.ServiceResponse;
import exchange.platform.common.http.HttpStatus;

@Service
public class AuthServiceImpl implements AuthService {

	
	@Autowired
    private TokenProperties tokenProperties;
    @Autowired
    private TokenFactory    tokenFactory;
    @Autowired
    private TokenExtractor  tokenExtractor;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserRoleService userRoleService;
    
    
	@Override
	public Object refreshToken(HttpServletRequest request) {
		String tokenPayload = tokenExtractor.extract(request.getHeader(RequestUtils.TOKEN_HEADER_PARAM));
        RawAccessToken rawToken = new RawAccessToken(tokenPayload);
        RefreshToken refreshToken = null;
        try {
        	refreshToken = RefreshToken.create(rawToken, tokenProperties.getSigningKey()).orElseThrow(() -> new InvalidTokenException("Token验证失败"));
        }catch (Exception e) {
			return new ServiceResponse(HttpStatus.BAD_REQUEST.value(), "Token Verification Failure");
		}

        String subject = refreshToken.getSubject();
        UserInfo user = Optional.ofNullable(userInfoService.findUserByUserName(subject)).orElseThrow(() -> new UsernameNotFoundException("用户未找到: " + subject));
        List<UserRole> roles = Optional.ofNullable(userRoleService.getRoleByUser(user)).orElseThrow(() -> new InsufficientAuthenticationException("用户没有分配角色"));
        List<GrantedAuthority> authorities = roles.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.authority()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user.getUserName(), authorities);
        return tokenFactory.createAccessToken(userContext);
	}


	@Override
	public boolean verify(String tokens) {
		boolean isVerified = true;
		try {
			new RawAccessToken(tokens).parseClaims(tokenProperties.getSigningKey());
		} catch (Exception e) {
			isVerified = false;
		}
		return isVerified;
	}
}
