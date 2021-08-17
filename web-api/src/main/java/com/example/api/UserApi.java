package com.example.api;

import com.example.api.req.LoginReq;
import com.example.api.req.SetUserReq;
import com.example.api.req.SignInReq;
import com.example.api.vo.TokenVo;
import com.example.api.vo.UidVo;
import com.example.api.vo.UserInfoVo;
import com.example.common.Response;
import com.example.model.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author zjianfa
 */
@Api(value = "User 接口")
public interface UserApi{

    @ApiOperation("注册系统时获取验证码")
    @PostMapping(value = "/user/sign-send-code", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Boolean> sendCode(@RequestBody @Validated SignInReq req);

    @ApiOperation("注册系统")
    @PostMapping(value = "/user/sign-in", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<UidVo> signIn(@RequestBody @Validated SignInReq req);

    @ApiOperation("登录系统")
    @PostMapping(value = "/user/login-in", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<TokenVo> loginIn(@RequestBody @Validated LoginReq req);

    @ApiOperation("登出系统")
    @GetMapping(value = "/user/login-out", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Void> loginOut(@RequestBody @Validated User user);

    @ApiOperation("获取全部用户")
    @GetMapping(value = "/user/get-all-user-list", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<List<User>> getAllUserList();

    @ApiOperation("获取个人信息")
    @PostMapping(value = "/user/get-person-info", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<UserInfoVo> getPersonInfo();

    @ApiOperation("新增用户")
    @PostMapping(value = "/user/set-user-info", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Boolean> setUserInfo(@RequestBody @Validated SetUserReq req);

    @ApiOperation("用户修改个人信息")
    @PostMapping(value = "/user/modified-user-info", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Boolean> modifiedUserInfo(@RequestBody @Validated SetUserReq req);
}
