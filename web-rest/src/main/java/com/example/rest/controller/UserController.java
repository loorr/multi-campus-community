package com.example.rest.controller;

import com.example.api.UserApi;
import com.example.api.req.LoginReq;
import com.example.api.req.SetUserReq;
import com.example.api.req.SignInReq;
import com.example.api.vo.TokenVo;
import com.example.api.vo.UidVo;
import com.example.api.vo.UserInfoVo;
import com.example.common.Response;
import com.example.model.entity.User;
import com.example.rest.BaseController;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zjianfa
 */
@RestController
public class UserController extends BaseController implements UserApi {

    @Override
    public Response<Boolean> sendCode(SignInReq req) {
        return null;
    }

    @Override
    public Response<UidVo> signIn(SignInReq req) {
        return null;
    }

    @Override
    public Response<TokenVo> loginIn(LoginReq req) {
        return null;
    }

    @Override
    public Response<Void> loginOut(User user) {
        return null;
    }

    @Override
    public Response<List<User>> getAllUserList() {
        return null;
    }

    @Override
    public Response<UserInfoVo> getPersonInfo() {
        return null;
    }

    @Override
    public Response<Boolean> setUserInfo(SetUserReq req) {
        return null;
    }

    @Override
    public Response<Boolean> modifiedUserInfo(SetUserReq req) {
        return null;
    }
}
