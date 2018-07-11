package exchange.platform.authentication.security.auth.token;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import exchange.platform.authentication.security.auth.AuthenticationToken;
import exchange.platform.authentication.security.auth.token.extractor.TokenExtractor;
import exchange.platform.authentication.security.model.token.RawAccessToken;
import exchange.platform.authentication.util.UriUtil;



/**
 * 
 * Description:执行Token的验证
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午4:38:44</p>
 * @author Tony
 * @version 1.0
 *
 */
public class TokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    
	private final AuthenticationFailureHandler failureHandler;
    private final TokenExtractor tokenExtractor;
    
    public TokenAuthenticationProcessingFilter(AuthenticationFailureHandler failureHandler, 
            TokenExtractor tokenExtractor, RequestMatcher matcher) {
        super(matcher);
        this.failureHandler = failureHandler;
        this.tokenExtractor = tokenExtractor;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
//        String tokenPayload = request.getHeader(AuthUtil.TOKEN_HEADER_PARAM);
//        RawAccessToken token = new RawAccessToken(tokenExtractor.extract(request.getHeader(AuthUtil.TOKEN_HEADER_PARAM)));
        return getAuthenticationManager()
        		.authenticate(
        				new AuthenticationToken(
        						new RawAccessToken(
        								tokenExtractor.extract(
        										request.getHeader(UriUtil.TOKEN_HEADER_PARAM)))));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
