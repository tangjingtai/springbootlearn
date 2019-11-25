package com.jt.springbootlearn.config;

import com.jt.springbootlearn.util.TokenProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private TokenProvider tokenProvider;
    private UserDetailsService userDetailsService;

    private AuthTokenConfigurer securityConfigurerAdapter() {
        return new AuthTokenConfigurer(tokenProvider, userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login","/webjars/**", "/asserts/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .and().apply(securityConfigurerAdapter());
    }
}
