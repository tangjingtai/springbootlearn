package com.jt.springbootlearn.controller;

import com.jt.springbootlearn.exception.AuthenticateException;
import com.jt.springbootlearn.security.LoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("all")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public LoginToken authorize(@RequestParam("username") String username, @RequestParam("password") String password){
        if(username == null || username.length() == 0 || password == null || password.length() == 0)
            throw new AuthenticateException("username or password cannot null or empty.");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = null;
        try {
            authentication = this.authenticationManager.authenticate(token);
        }
        catch (AuthenticationException e){
            throw new AuthenticateException(e.getMessage());
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails details = this.userDetailsService.loadUserByUsername(username);

        String tokenStr = username +":"+password;   // 修改成JWT
        LoginToken loginToken = new LoginToken();
        loginToken.setToken(tokenStr);
        loginToken.setExpire(30 * 24 * 60L);
        return loginToken;
    }

}
