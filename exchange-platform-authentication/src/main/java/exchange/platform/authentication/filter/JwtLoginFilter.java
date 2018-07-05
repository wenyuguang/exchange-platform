package exchange.platform.authentication.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import exchange.platform.authentication.domain.Employee;
import exchange.platform.authentication.util.JwtUtils;
import exchange.platform.common.auth.AuthUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月2日 上午10:56:21</p>
 * @author Tony
 * @version 1.0
 *
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Employee employee = new Employee();
        employee.setId("a");
        employee.setPassword("1111");
        employee.setUsername("1111");
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        employee.getUsername(),
                        employee.getPassword(),
                        new ArrayList<>()
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Date date = new Date(System.currentTimeMillis() + 5 * 1000);
    	String token = Jwts.builder()
                .setSubject(((User) authResult.getPrincipal()).getUsername())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, AuthUtil.PRIVATESECRET)
                .compact();
    	System.err.println(date);
        response.addHeader(AuthUtil.AUTHORIZATION, JwtUtils.getTokenHeader(token));
    }
}
