package exchange.platform.authentication.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import exchange.platform.authentication.filter.JwtAuthenticationFilter;
import exchange.platform.authentication.filter.JwtLoginFilter;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月2日 上午10:32:49</p>
 * @author Tony
 * @version 1.0
 *
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String LOGIN_PAGE = "/login";

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage(LOGIN_PAGE)
                .and()
                .addFilter(new JwtLoginFilter(authenticationManager()))
                .addFilter(new JwtAuthenticationFilter(authenticationManager()));
    }

}
