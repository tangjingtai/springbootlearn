package com.jt.springbootlearn.exception;

/**
 * 认证错误
 */
public class AuthenticateException  extends RuntimeException {

    public AuthenticateException(String message) {
        super(message);
    }

}
