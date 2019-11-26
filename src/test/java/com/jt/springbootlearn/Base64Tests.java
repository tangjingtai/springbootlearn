package com.jt.springbootlearn;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Tests {
    @Test
    public void testEncode() throws UnsupportedEncodingException {
        final Base64.Decoder decoder = Base64.getDecoder();
        final Base64.Encoder encoder = Base64.getEncoder();
        final String text = "字串文字";
        final byte[] textByte = text.getBytes("UTF-8");

        final String encodedText = encoder.encodeToString(textByte);
        System.out.println(encodedText);
    }
}
