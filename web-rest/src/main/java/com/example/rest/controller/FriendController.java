package com.example.rest.controller;


import com.example.api.FriendApi;
import com.example.api.req.AddOrDeleteUserReq;
import com.example.api.req.GetAllFriendReq;
import com.example.api.type.FriendState;
import com.example.api.vo.FriendShipVo;
import com.example.api.vo.FriendStateVo;
import com.example.common.Response;
import com.example.common.annotations.NeedToken;
import com.example.api.common.ChatException;
import com.example.core.service.FriendService;
import com.example.rest.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zjianfa
 */
@Slf4j
@RestController
public class FriendController extends BaseController implements FriendApi {

    @Autowired
    private FriendService friendService;

    @NeedToken
    @Override
    public Response<FriendStateVo> addFriend(AddOrDeleteUserReq req) {
        requestInfoLog(req);
        req.setUid(getLoginUid());
        FriendStateVo friendStateVo = new FriendStateVo();
        try {
            FriendState friendState = friendService.addFriend(req);
            friendStateVo.setFriendState(friendState);
        }catch (ChatException e){
            return Response.getFail(e.getCode(), e.getMsg());
        }
        return Response.getOk(friendStateVo);
    }

    @NeedToken
    @Override
    public Response<Boolean> deleteFriend(AddOrDeleteUserReq req) {
        Long fromUid = getLoginUid();
        req.setUid(fromUid);
        boolean result = friendService.deleteFriend(req);
        if (!result){
            return Response.getFail();
        }
        return Response.getOk(true);
    }

    @NeedToken
    @Override
    public Response<FriendShipVo> getAllFriend(GetAllFriendReq req) {
        req.setUid(getLoginUid());
        try{
            FriendShipVo friendShipVo = friendService.getAllFriend(req);
            return Response.getOk(friendShipVo);
        }catch (ChatException e){
            return Response.getFail(e);
        }
    }
}
