package com.example.rest;

import lombok.extern.slf4j.Slf4j;
import com.example.common.ChatException;
import com.example.common.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.example.common.Constant.UID_ATTRIBUTE;

@Slf4j
@RestController
public class BaseController {

    @Autowired
    protected HttpServletRequest httpServletRequest;

    /**
     * 获取已登陆用户的个人信息
     * @return
     */
    protected Long getLoginUid(){
        Long uid;
        try {
            uid = (Long) httpServletRequest.getAttribute(UID_ATTRIBUTE);
        }catch (Exception e){
            throw new ChatException(ErrorCode.AUTH_ERROR);
        }
        if (uid == null){
            throw new ChatException(ErrorCode.AUTH_ERROR);
        }
        return uid;
    }

    /**
     * 打印请求信息
     * @param request
     */
    protected void requestInfoLog(Object request){
        log.info("{} model {}", httpServletRequest.getRequestURI(),  request);
    }

}
