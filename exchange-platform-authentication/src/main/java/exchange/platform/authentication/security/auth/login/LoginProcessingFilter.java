package exchange.platform.authentication.security.auth.login;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import exchange.platform.authentication.security.exceptions.AuthMethodNotSupportedException;
import exchange.platform.common.auth.AuthUtil;


/**
 * 
 * Description:登录处理过滤器
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午4:37:19</p>
 * @author Tony
 * @version 1.0
 *
 */
public class LoginProcessingFilter extends AbstractAuthenticationProcessingFilter {
    private static Logger logger = LoggerFactory.getLogger(LoginProcessingFilter.class);

    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;

    public LoginProcessingFilter(String defaultProcessUrl, AuthenticationSuccessHandler successHandler,
                                 AuthenticationFailureHandler failureHandler) {
        super(defaultProcessUrl);
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        if (!HttpMethod.POST.name().equals(request.getMethod())) {
            if (logger.isDebugEnabled()) {
                logger.debug("Authentication method not supported. Request method: " + request.getMethod());
            }
            throw new AuthMethodNotSupportedException("Authentication method not supported");
        }
        String username = null;
        String password = null;
        try {
        	//Authorization:Basic base64(账号:密码)
        	String baseHeader = request.getHeader(AuthUtil.AUTHORIZATION)
        			.replace(AuthUtil.AUTHORIZATION_BASIC_HEADER_PREFIX, "");
        	String serviceName = request.getHeader(AuthUtil.SERVICE_NAME);
        	String [] credentials = new String(Base64.decodeBase64(baseHeader),"UTF-8").split(":");
        	username = credentials[0] + AuthUtil.SEPARATOR + serviceName;
        	password = credentials[1];
        }catch(Exception e) {
        	throw new BadCredentialsException("Basic Head information coding error");
        }
        LoginRequest loginRequest = new LoginRequest(username, password);
        if (StringUtils.isBlank(loginRequest.getUsername()) || StringUtils.isBlank(loginRequest.getPassword())) {
            throw new AuthenticationServiceException("Username or Password not provided");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword());
        return this.getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
