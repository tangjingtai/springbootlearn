package com.jt.springbootlearn.config;

import com.jt.springbootlearn.filter.AuthTokenFilter;
import com.jt.springbootlearn.util.TokenProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AuthTokenConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private TokenProvider tokenProvider;
    private UserDetailsService userDetailsService;

    public AuthTokenConfigurer(TokenProvider tokenProvider, UserDetailsService userDetailsService) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        AuthTokenFilter authTokenFilter = new AuthTokenFilter(tokenProvider, userDetailsService);
        builder.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
