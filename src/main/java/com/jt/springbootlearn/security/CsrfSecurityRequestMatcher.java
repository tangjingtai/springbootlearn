package com.jt.springbootlearn.security;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class CsrfSecurityRequestMatcher implements RequestMatcher {
    private final HashSet<String> allowedMethods = new HashSet<>(
            Arrays.asList("GET", "HEAD", "TRACE", "OPTIONS"));

    private final List<String> excludeUrls = Arrays.asList("/authenticate");
    private final List<AntPathRequestMatcher> excludeMatchers = new ArrayList<>();

    public CsrfSecurityRequestMatcher() {
        if(this.excludeUrls!=null && !excludeUrls.isEmpty()){
            for(String url : excludeUrls){
                AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
                excludeMatchers.add(matcher);
            }
        }
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        if(allowedMethods.contains(request.getMethod()))
            return false;

        if (this.excludeMatchers != null && this.excludeMatchers.size() > 0) {
            String servletPath = request.getServletPath();
            request.getParameter("");
            for (AntPathRequestMatcher matcher : this.excludeMatchers) {
                if (matcher.matches(request)) {
                    return false;
                }
            }
        }
        return true;
    }
}
