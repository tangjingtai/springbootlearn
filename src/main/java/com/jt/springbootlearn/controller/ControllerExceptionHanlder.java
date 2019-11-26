package com.jt.springbootlearn.controller;

import com.jt.springbootlearn.exception.AuthenticateException;
import com.jt.springbootlearn.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHanlder {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    public Map<String, Object> handleUserNotExistException(Exception ex){
        Map<String, Object> map = new HashMap<>();
        map.put("code", "user.notexist");
        map.put("message", "用户不存在");

        return map;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    @ExceptionHandler(AuthenticateException.class)
    public Map<String, Object> handleAuthenticateException(AuthenticateException ex, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", System.currentTimeMillis());
        map.put("status", HttpStatus.UNAUTHORIZED.value());
        map.put("error", "Unauthorized");
        map.put("message", ex.getMessage());
        map.put("path", request.getServletPath());

        return map;
    }
}
