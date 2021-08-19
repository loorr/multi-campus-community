package com.example.core.service.impl;


import com.example.api.req.AddOrDeleteUserReq;
import com.example.api.type.FriendState;
import com.example.common.ChatErrorCode;
import com.example.common.exception.ChatException;
import com.example.core.service.FriendService;
import com.example.dao.FriendMapper;
import com.example.model.entity.Friends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendMapper friendMapper;

    @Override
    public FriendState addFriend(AddOrDeleteUserReq req) {
        int row = 0;
        try{
            row = friendMapper.addFriend(req);
        }catch (DuplicateKeyException e){
            throw new ChatException(ChatErrorCode.DUPLICATE_ERROR);
        }
        if (row != 1){
            throw new ChatException(ChatErrorCode.OPERATION_ERROR);
        }
        Friends friends = friendMapper.getFriendShip(req.getFromUid(), req.getToUid());
        if (friends == null || !req.getFromUid().equals(friends.getToUid())){
            return FriendState.FOLLWOWING;
        }
        return FriendState.BOTH_WAY;
    }

    @Override
    public Boolean deleteFriend(AddOrDeleteUserReq req) {
        int row = friendMapper.deletFriendShip(req.getToUid(), req.getFromUid());
        return row == 1;
    }
}
