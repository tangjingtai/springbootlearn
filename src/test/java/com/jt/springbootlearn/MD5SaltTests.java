package com.jt.springbootlearn;

import com.jt.springbootlearn.util.MD5Salt;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class MD5SaltTests {
    @Test
    public void testEncryptedPwd() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String pwd = "123456";
        String encryptedPwd = MD5Salt.getEncryptedPwd(pwd);
        System.out.println("待加密值："+pwd +", 加密后的值："+encryptedPwd);

        boolean b = MD5Salt.validPassword(pwd, encryptedPwd);
        System.out.println("密码校验结果："+b);
    }
}
