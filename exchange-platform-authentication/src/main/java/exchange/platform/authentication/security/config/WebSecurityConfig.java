package exchange.platform.authentication.security.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.google.common.collect.Lists;

import exchange.platform.authentication.security.RestAuthenticationEntryPoint;
import exchange.platform.authentication.security.auth.login.LoginAuthenticationProvider;
import exchange.platform.authentication.security.auth.login.LoginProcessingFilter;
import exchange.platform.authentication.security.auth.token.SkipPathRequestMatcher;
import exchange.platform.authentication.security.auth.token.TokenAuthenticationProcessingFilter;
import exchange.platform.authentication.security.auth.token.TokenAuthenticationProvider;
import exchange.platform.authentication.security.auth.token.extractor.TokenExtractor;
import exchange.platform.common.auth.RequestUtils;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午5:34:41</p>
 * @author Tony
 * @version 1.0
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private LoginAuthenticationProvider  loginAuthenticationProvider;
    @Autowired
    private TokenAuthenticationProvider  tokenAuthenticationProvider;
    @Autowired
    private TokenExtractor               tokenExtractor;

    private LoginProcessingFilter buildLoginProcessingFilter() throws Exception {
        LoginProcessingFilter filter = new LoginProcessingFilter(RequestUtils.FORM_BASED_LOGIN_ENTRY_POINT, successHandler, failureHandler);
        filter.setAuthenticationManager(super.authenticationManager());
        return filter;
    }

    private TokenAuthenticationProcessingFilter buildTokenAuthenticationProcessingFilter() throws Exception {
//        List<String> list = Lists.newArrayList(TOKEN_BASED_AUTH_ENTRY_POINT, MANAGE_TOKEN_BASED_AUTH_ENTRY_POINT);
    	List<String> list = Lists.newArrayList(RequestUtils.TOKEN_BASED_AUTH_ENTRY_POINT);
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(list);
        TokenAuthenticationProcessingFilter filter = new TokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, matcher);
        filter.setAuthenticationManager(super.authenticationManager());
        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(loginAuthenticationProvider);
        auth.authenticationProvider(tokenAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 因为使用的是JWT，因此这里可以关闭csrf了
                .exceptionHandling()
                .authenticationEntryPoint(this.authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(RequestUtils.FORM_BASED_LOGIN_ENTRY_POINT).permitAll() // Login end-point
                .antMatchers(RequestUtils.TOKEN_REFRESH_ENTRY_POINT).permitAll() // Token refresh end-point
                .and()
                .authorizeRequests()
                .antMatchers(RequestUtils.TOKEN_BASED_AUTH_ENTRY_POINT).authenticated() // Protected API End-points
//                .antMatchers(MANAGE_TOKEN_BASED_AUTH_ENTRY_POINT).hasAnyRole(RoleEnum.ADMIN.desc())
                .and()
                .addFilterBefore(buildLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    
    @Bean
    protected BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
