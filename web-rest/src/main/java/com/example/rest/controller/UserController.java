package com.example.rest.controller;

import com.example.api.UserApi;
import com.example.api.req.LoginReq;
import com.example.api.req.SetUserReq;
import com.example.api.req.SignInReq;
import com.example.api.vo.TokenVo;
import com.example.api.vo.UidVo;
import com.example.api.vo.UserInfoVo;
import com.example.api.common.ChatErrorCode;
import com.example.common.Response;
import com.example.common.TokenUtil;
import com.example.common.annotations.NeedToken;
import com.example.api.common.ChatException;
import com.example.core.common.impl.RedisUserServiceImpl;
import com.example.core.service.UserService;
import com.example.model.entity.User;
import com.example.rest.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zjianfa
 */
@RestController
public class UserController  extends BaseController implements UserApi {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUserServiceImpl redisUserService;

    @Override
    public Response<Boolean> sendCode(SignInReq req) {
        try {
            userService.sendCode(req);
        }catch (ChatException e){
            return Response.getFail(e.getCode(), e.getMsg());
        }
        return Response.getOk(true);
    }

    @Override
    public Response<UidVo> signIn(SignInReq req) {
        UidVo uidVo = new UidVo();
        if (!StringUtils.hasLength(req.getCode())){
            return Response.getFail(ChatErrorCode.NO_CODE);
        }
        try {
            Long uid = userService.signUser(req);
            uidVo.setUid(uid);
        }catch (ChatException e){
            return Response.getFail(e.getCode(), e.getMsg());
        }
        return Response.getOk(uidVo);
    }

    @Override
    public Response<TokenVo> loginIn(LoginReq req) {
        User dbUser = null;
        if (req.getUid() == null && !StringUtils.hasLength(req.getEmail())){
            return Response.getFail(ChatErrorCode.AUTH_ERROR);
        }
        if (req.getUid() != null){
            dbUser = userService.findUserByUid(req.getUid());
        }else{
            dbUser = userService.findUserByEmail(req.getEmail());
        }
        redisUserService.setUserPasswordByUid(dbUser.getUid(), dbUser.getPassword());
        if (dbUser == null || !dbUser.getPassword().equals(req.getPassword())){
            return Response.getFail(ChatErrorCode.AUTH_ERROR);
        }
        TokenVo tokenVo = new TokenVo();
        String token = TokenUtil.createToken(String.valueOf(dbUser.getUid()), dbUser.getPassword());
        tokenVo.setToken(token);
        return Response.getOk(tokenVo);
    }

    @NeedToken
    @Override
    public Response<Void> loginOut(User user) {
          return null;
    }

    @Override
    @NeedToken
    public Response<List<User>> getAllUserList() {
        System.out.println(getLoginUid());
        List<User> userList = userService.getAllUser(5);
        return Response.getOk(userList);
    }

    @NeedToken
    @Override
    public Response<UserInfoVo> getPersonInfo() {
        // TODO
        return null;
    }

    @NeedToken
    @Override
    public Response<Boolean> setUserInfo(SetUserReq req) {
        System.out.println(req.toString());
        return Response.getOk(true);
    }

    @NeedToken
    @Override
    public Response<Boolean> modifiedUserInfo(SetUserReq req) {
        return null;
    }
}
