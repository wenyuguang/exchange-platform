package exchange.platform.authentication.contorller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import exchange.platform.authentication.domain.UserInfo;
import exchange.platform.authentication.domain.UserRole;
import exchange.platform.authentication.security.auth.token.extractor.TokenExtractor;
import exchange.platform.authentication.security.config.TokenProperties;
import exchange.platform.authentication.security.exceptions.InvalidTokenException;
import exchange.platform.authentication.security.model.UserContext;
import exchange.platform.authentication.security.model.token.RawAccessToken;
import exchange.platform.authentication.security.model.token.RefreshToken;
import exchange.platform.authentication.security.model.token.TokenFactory;
import exchange.platform.authentication.service.UserInfoService;
import exchange.platform.authentication.service.UserRoleService;
import exchange.platform.authentication.util.UriUtil;
import exchange.platform.common.code.ServiceResponse;
import exchange.platform.common.http.HttpStatus;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午5:35:31</p>
 * @author Tony
 * @version 1.0
 *
 */
@RestController
public class AuthController {

	@Autowired
    private TokenProperties tokenProperties;
    @Autowired
    private TokenFactory tokenFactory;
    @Autowired
    private TokenExtractor tokenExtractor;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserRoleService userRoleService;


    @GetMapping("/test1")
    public String test1() {
        return "test1";
    }

    @GetMapping("/auth/test2")
    public String test2() {
        return "test2";
    }

    /**
     * 
     * @function "/auth/refresh_token"
     * @author Tony
     * @creaetime 2018年7月9日 下午5:39:26
     * @param request
     * @return
     */
    @GetMapping(UriUtil.TOKEN_REFRESH_ENTRY_POINT)
    public Object refreshToken(HttpServletRequest request) {
        String tokenPayload = tokenExtractor.extract(request.getHeader(UriUtil.TOKEN_HEADER_PARAM));
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


}
