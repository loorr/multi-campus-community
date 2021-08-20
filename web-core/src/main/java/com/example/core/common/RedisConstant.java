package com.example.core.common;

public class RedisConstant {
    public static final String CONNECTOR_CHAR = ":";
    /** 验证码前缀 */
    public static final String AUTH_CODE_PREFIX = "AUTHCODE";
    public static final String UID = "UID";

    public static final String ESSAY_LIKE_NUM = "ESSAY:LIKE:%s";
    public static final String ESSAY_DISLIKE_NUM = "ESSAY::DISLIKE:%s";
    /** 记录用户对文章的喜欢或者不喜欢,长度 11 01 10 00 */
    public static final String USER_ESSAY_STATE = "USER:ESSAY:%s:%s";

    public static String getRedisKey(String format, Object... keys){
        return String.format(format, keys);
    }

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

