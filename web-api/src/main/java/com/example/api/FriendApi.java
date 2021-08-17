package com.example.api;

import com.example.api.req.AddOrDeleteUserReq;
import com.example.api.req.GetAllFriendReq;
import com.example.api.vo.FriendShipVo;
import com.example.api.vo.FriendStateVo;
import com.example.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author zjianfa
 */
@Api(value = "Friend 好友关系")
public interface FriendApi {

    @ApiOperation("关注他人 或者加好友")
    @PostMapping(value = "/friend/add-friend", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<FriendStateVo> addFriend(@RequestBody @Validated AddOrDeleteUserReq req);

    @ApiOperation("删除好友")
    @PostMapping(value = "/friend/delete-friend", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Boolean> deleteFriend(@RequestBody @Validated AddOrDeleteUserReq req);

    @ApiOperation("获取用户的所有好友")
    @PostMapping(value = "/friend/get-friend", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<FriendShipVo> getAllFriend(@RequestBody @Validated GetAllFriendReq req);

}
