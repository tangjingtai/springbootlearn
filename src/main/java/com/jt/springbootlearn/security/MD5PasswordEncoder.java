package com.jt.springbootlearn.security;

import com.jt.springbootlearn.util.MD5Salt;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class MD5PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        if(rawPassword == null || rawPassword.length() == 0)
            throw new IllegalArgumentException("rawPassword cannot be empty or null.");
        try {
            return MD5Salt.getEncryptedPwd(rawPassword.toString());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException("encode fail.", e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if(rawPassword == null || rawPassword.length() == 0)
            throw new IllegalArgumentException("rawPassword cannot be empty or null.");
        if(encodedPassword == null || encodedPassword.length() == 0)
            throw new IllegalArgumentException("encodedPassword cannot be empty or null.");

        if(encodedPassword.length() < MD5Salt.DEFAULT_SALT_LENGTH)
            throw new IllegalArgumentException("encodedPassword length error.");

        try {
            return MD5Salt.validPassword(rawPassword.toString(), encodedPassword);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException("password matches fail.", e);
        }
    }
}
