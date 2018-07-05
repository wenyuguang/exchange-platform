package exchange.platform.authentication.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import exchange.platform.authentication.util.JwtUtils;
import exchange.platform.common.auth.AuthUtil;
import io.jsonwebtoken.Jwts;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月2日 上午10:56:06</p>
 * @author Tony
 * @version 1.0
 *
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(AuthUtil.AUTHORIZATION);

        if (header == null || !header.startsWith(JwtUtils.getAuthorizationHeaderPrefix())) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getUsernamePasswordAuthenticationToken(header);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String token) {
    	try {
    		String user = Jwts.parser()
    				.setSigningKey(AuthUtil.PRIVATESECRET)
    				.parseClaimsJws(token.replace(JwtUtils.getAuthorizationHeaderPrefix(), ""))
    				.getBody()
    				.getSubject();
    		System.err.println(user);
    		if (null != user) {
    			return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}

        return null;
    }
}
