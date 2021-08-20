package com.example.rest.config;

import com.auth0.jwt.interfaces.Claim;
import com.example.api.common.ChatErrorCode;
import com.example.common.TokenUtil;
import com.example.common.annotations.NeedToken;
import com.example.common.annotations.PassToken;
import com.example.common.exception.AuthExcetption;
import com.example.core.common.RedisService;
import com.example.core.service.UserService;
import com.example.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author zjianfa
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    public final static String TOKEN_HEADER = "token";
    public final static String UID_ATTRIBUTE = "uid";

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object){
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader(TOKEN_HEADER);
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method = handlerMethod.getMethod();

        // 检查是否有 passtoken 注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        // 检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(NeedToken.class)) {
            NeedToken userLoginToken = method.getAnnotation(NeedToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new AuthExcetption(ChatErrorCode.AUTH_ERROR);
                }
                Map<String, Claim> claimMap = TokenUtil.verifyToken(token);
                // 获取 token 中的 user id
                String userId =  claimMap.get(UID_ATTRIBUTE).asString();
                String password = null;
                password = redisService.getUserPasswordByUid(Long.valueOf(userId));
                if (password == null || password.length() == 0) {
                    User user = userService.findUserByUid(Long.valueOf(userId));
                    if (user == null || user.getPassword() == null){
                        throw new RuntimeException("用户不存在，请重新登录");
                    }
                    redisService.setUserPasswordByUid(user.getUid(), user.getPassword());
                    password = user.getPassword();
                }
                String newToken = TokenUtil.verifyToken(claimMap, password);
                if (!StringUtils.hasLength(newToken)){
                    return false;
                }
                httpServletRequest.setAttribute(UID_ATTRIBUTE, Long.valueOf(userId));
                httpServletResponse.setHeader(TOKEN_HEADER, newToken);
            }
        }
        return true;
    }
}
