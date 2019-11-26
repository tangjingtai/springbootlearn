package com.jt.springbootlearn.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

/**
 * MD5加盐加密，固定12位盐值
 */
public class MD5Salt {

    public static final Integer DEFAULT_SALT_LENGTH = 12;

    /**
     * 获得加密后的16进制形式口令
     * @param password 原始密码
     * @return MD5加密之后的密码
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String getEncryptedPwd(String password)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //声明加密后的口令数组变量
        byte[] encodePassword = null;
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[DEFAULT_SALT_LENGTH];

        random.nextBytes(salt); //将随机数放入盐变量中

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(salt); //将盐数据传入消息摘要对象
        md.update(password.getBytes("UTF-8"));
        byte[] digest = md.digest();

        encodePassword = new byte[digest.length + DEFAULT_SALT_LENGTH];
        System.arraycopy(salt, 0, encodePassword, 0, DEFAULT_SALT_LENGTH);
        System.arraycopy(digest, 0, encodePassword, DEFAULT_SALT_LENGTH, digest.length);

        return Base64.getEncoder().encodeToString(encodePassword);
    }

    /**
     * 验证口令是否合法
     * @param password 原始密码
     * @param passwordInDb 数据库中存储的经过MD5加密的密码
     * @return true 表示密码匹配，false表示密码不匹配
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static boolean validPassword(String password, String passwordInDb)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] pwdInDb = Base64.getDecoder().decode(passwordInDb);
        //声明盐变量
        byte[] salt = new byte[DEFAULT_SALT_LENGTH];
        System.arraycopy(pwdInDb, 0, salt, 0, DEFAULT_SALT_LENGTH);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(salt);
        md.update(password.getBytes("UTF-8"));
        byte[] digest = md.digest();
        byte[] digestInDb = new byte[pwdInDb.length - DEFAULT_SALT_LENGTH];
        System.arraycopy(pwdInDb, DEFAULT_SALT_LENGTH, digestInDb, 0, digestInDb.length);
        return Arrays.equals(digest, digestInDb);
    }
}
