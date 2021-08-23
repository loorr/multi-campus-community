package com.example.core.service;


import com.example.api.req.AddOrDeleteUserReq;
import com.example.api.req.GetAllFriendReq;
import com.example.api.type.FriendState;
import com.example.api.vo.FriendShipVo;

public interface FriendService {

    FriendState addFriend(AddOrDeleteUserReq req);

    Boolean deleteFriend(AddOrDeleteUserReq req);

    FriendShipVo getAllFriend(GetAllFriendReq req);
}
