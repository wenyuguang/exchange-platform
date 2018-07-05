package exchange.platform.web.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 * WebSecurityConfigescription:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月5日 上午10:25:09</p>
 * @author Tony
 * @version 1.0
 *
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	UserDetailsService systemUserDetailsService() {
		return new SystemUserDetailsService();
	}

	protected void configure(HttpSecurity http) throws Exception {
		((HttpSecurity) ((FormLoginConfigurer) ((HttpSecurity) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) http
				.authorizeRequests().antMatchers(new String[] { "/css/**", "/index" })).permitAll()
						.antMatchers(new String[] { "/user/**" })).hasRole("USER").and()).formLogin()
								.loginPage("/login").failureUrl("/login-error")).and()).logout();
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(systemUserDetailsService());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}