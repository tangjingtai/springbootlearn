package com.jt.springbootlearn.config;

import com.jt.springbootlearn.security.CsrfSecurityRequestMatcher;
import com.jt.springbootlearn.security.MD5PasswordEncoder;
import com.jt.springbootlearn.util.TokenProvider;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    private AuthTokenConfigurer securityConfigurerAdapter() {
        return new AuthTokenConfigurer(tokenProvider, userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MD5PasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http
                .csrf().requireCsrfProtectionMatcher(new CsrfSecurityRequestMatcher())  // 自定义是否需要csrf检查的匹配规则
                .and().authorizeRequests()
                .antMatchers("/authenticate","/login","/webjars/**", "/asserts/**" ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .and().apply(securityConfigurerAdapter());
    }
}
