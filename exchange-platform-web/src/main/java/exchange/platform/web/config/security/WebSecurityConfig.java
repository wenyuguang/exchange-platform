package exchange.platform.web.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import exchange.platform.web.util.RoleUtil;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月5日 下午2:46:43</p>
 * @author Tony
 * @version 1.0
 *
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService systemUserDetailsService() {
		return new SystemUserDetailsService();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/user/**").access("hasRole('"+RoleUtil.USER+"')")
			.antMatchers("/admin/**").access("hasRole('"+RoleUtil.ADMIN+"')")
			.antMatchers("/resources/**")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.and()
				.formLogin()
					.loginPage("/login")//.successHandler(customSuccessHandler)
					.usernameParameter("username")
					.passwordParameter("password")
			.permitAll()
			.and()
			.logout()
			.permitAll();
			//.and().exceptionHandling().accessDeniedPage("/access_denied");
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(systemUserDetailsService()).passwordEncoder(new BCryptPasswordEncoder());;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}