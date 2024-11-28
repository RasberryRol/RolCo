package com.rolco.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionUtil {
    public static String encode(String value) throws UnsupportedEncodingException{
        Base64.Encoder e = Base64.getEncoder();
        return e.encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    public static String decode(String encrypted) throws UnsupportedEncodingException {
        byte[] b = Base64.getDecoder().decode(encrypted);
        return new String(b, StandardCharsets.UTF_8);
    }
}