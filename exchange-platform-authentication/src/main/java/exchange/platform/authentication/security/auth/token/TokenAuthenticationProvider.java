package exchange.platform.authentication.security.auth.token;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import exchange.platform.authentication.security.auth.AuthenticationToken;
import exchange.platform.authentication.security.config.TokenProperties;
import exchange.platform.authentication.security.model.UserContext;
import exchange.platform.authentication.security.model.token.RawAccessToken;
import exchange.platform.authentication.security.model.token.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * 
 * Description:使用 {@link AuthenticationProvider} 的接口提供实现 {@link Token} 身份验证的实例
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午4:39:09</p>
 * @author Tony
 * @version 1.0
 *
 */
@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

	private Logger logger = LoggerFactory.getLogger(TokenAuthenticationProvider.class);
	private final TokenProperties tokenProperties;

	@Autowired
	public TokenAuthenticationProvider(TokenProperties tokenProperties) {
		this.tokenProperties = tokenProperties;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		RawAccessToken rawAccessToken = (RawAccessToken) authentication.getCredentials();
		long startTime = System.currentTimeMillis();
		Jws<Claims> jwsClaims = rawAccessToken.parseClaims(tokenProperties.getSigningKey());
		logger.debug("[验证Token消耗时间] - [{}]", (System.currentTimeMillis() - startTime));
		String subject = jwsClaims.getBody().getSubject();
		@SuppressWarnings("unchecked")
		List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
		List<GrantedAuthority> authorities = scopes.stream().map(SimpleGrantedAuthority::new).collect(toList());
		UserContext context = UserContext.create(subject, authorities);
		return new AuthenticationToken(context, context.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (AuthenticationToken.class.isAssignableFrom(authentication));
	}
}
