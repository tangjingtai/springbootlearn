package com.jt.springbootlearn.security;

public class LoginToken {
    /**
     * 登录的token
     */
    private String token;
    /**
     * 过期时间，单位：秒
     */
    private Long expire;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }
}
