package com.example.core.service;


import com.example.api.req.AddOrDeleteUserReq;
import com.example.api.type.FriendState;

public interface FriendService {

    FriendState addFriend(AddOrDeleteUserReq req);

    Boolean deleteFriend(AddOrDeleteUserReq req);

}
