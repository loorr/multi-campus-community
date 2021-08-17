package com.example.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.SneakyThrows;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * token生成 验证的工具类
 * 第一部分：我们称它为头部（header），用于存放token类型和加密协议，一般都是固定的；
 * 第二部分：我们称其为载荷（payload），用户数据就存放在里面；
 * 第三部分：是签证（signature），主要用于服务端的验证；
 * https://zhuanlan.zhihu.com/p/141065758
 * @author zjianfa
 */
public class TokenUtil {
    /** 密钥 */
    private static final String SECRET = "sdjhakdhajdklsl;o653632";
    private static final String FIELD_ID = "uid";
    private static final String FIELD_PASSWORD = "password";
    /** 过期时间:秒 */
    private static final int EXPIRE = 60*60*3;
    private static Map<String, Object> map = new HashMap<>();
    static {
        map.put("alg", "HS256");
        map.put("typ", "JWT");
    }

    /**
     * 生成Token
     * @return
     * @throws Exception
     */
    public static String createToken(String userIdentification, String password) {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.SECOND, EXPIRE);
        Date expireDate = nowTime.getTime();

        String token = JWT.create()
                .withHeader(map)
                .withClaim(FIELD_ID,   userIdentification)
                .withClaim(FIELD_PASSWORD, password)
                .withIssuedAt(new Date())
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 验证Token
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, Claim> verifyToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        }catch (Exception e){
            throw new RuntimeException("凭证已过期，请重新登录");
        }
        return jwt.getClaims();
    }

    @SneakyThrows
    public static String verifyToken(Map<String, Claim> claimMap, String password) { ;
        if (!password.equals(claimMap.get(FIELD_PASSWORD).asString())){
            throw new RuntimeException("认证失败");
        }
        String uid =  claimMap.get(FIELD_ID).asString();
        return createToken(uid, password);
    }
}
