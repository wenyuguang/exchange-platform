package exchange.platform.authentication.security.auth.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import exchange.platform.authentication.security.exceptions.AuthMethodNotSupportedException;
import exchange.platform.authentication.security.exceptions.ExpiredTokenException;
import exchange.platform.common.code.ServiceResponse;


/**
 * 
 * Description:认证失败处理程序
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午4:36:16</p>
 * @author Tony
 * @version 1.0
 *
 */
@Component
public class AwareAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper mapper;

    @Autowired
    public AwareAuthenticationFailureHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        if (e instanceof BadCredentialsException) {
            mapper.writeValue(response.getWriter(), new ServiceResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
        } else if (e instanceof ExpiredTokenException) {
            mapper.writeValue(response.getWriter(), new ServiceResponse(HttpStatus.UNAUTHORIZED.value(), "Token has expired"));
        } else if (e instanceof AuthMethodNotSupportedException) {
            mapper.writeValue(response.getWriter(), new ServiceResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
        }
        mapper.writeValue(response.getWriter(), new ServiceResponse(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase()));
    }
}
