package com.example.core.common;

public class RedisConstant {
    public static final String CONNECTOR_CHAR = "::";
    /** 验证码前缀 */
    public static final String AUTH_CODE_PREFIX = "AUTHCODE";
    public static final String UID = "UID";

    public static String buildKey(String... args){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]);
            if (i != args.length - 1){
                sb.append(CONNECTOR_CHAR);
            }
        }
        return sb.toString();
    }

    public static String[] resolveKey(String key){
        return key.split(CONNECTOR_CHAR);
    }
}

