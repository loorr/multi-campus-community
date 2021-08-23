package com.example.core.service.impl;


import com.example.api.req.AddOrDeleteUserReq;
import com.example.api.req.GetAllFriendReq;
import com.example.api.type.FriendState;
import com.example.api.common.ChatErrorCode;
import com.example.api.common.ChatException;
import com.example.api.vo.FriendShipVo;
import com.example.api.vo.UserInfoVo;
import com.example.common.BaseErrorCode;
import com.example.core.service.FriendService;
import com.example.dao.FriendMapper;
import com.example.model.entity.Friends;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FriendServiceImpl implements FriendService {

    @Resource
    private FriendMapper friendMapper;

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
        Friends friends = friendMapper.getFriendShip(req.getUid(), req.getToUid());
        if (friends == null || !req.getUid().equals(friends.getToUid())){
            return FriendState.FOLLOWING;
        }
        return FriendState.BOTH_WAY;
    }

    @Override
    public Boolean deleteFriend(AddOrDeleteUserReq req) {
        int row = friendMapper.deleteFriendShip(req.getToUid(), req.getUid());
        return row == 1;
    }

    @Override
    public FriendShipVo getAllFriend(GetAllFriendReq req) {
        FriendShipVo friendShipVo = new FriendShipVo();
        switch (req.getRange()){
            case FOLLOWING:
                friendShipVo.setFollowing(getFollowing(req.getUid()));
                break;
            case FOLLOWED:
                friendShipVo.setFollowed(getFollowed(req.getUid()));
                break;
            case BOTH_WAY:
                friendShipVo = getAllFriendState(req.getUid(), FriendState.BOTH_WAY);
                break;
            case ALL:
                friendShipVo = getAllFriendState(req.getUid(), FriendState.ALL);
                break;
            default:
                throw new ChatException(BaseErrorCode.SYSTEM_BUSY);
        }
        friendShipVo.setUid(req.getUid());
        return friendShipVo;
    }

    /**
     * 获取该用户关注的人
     * @param uid
     * @return
     */
    private List<UserInfoVo> getFollowing(Long uid){
        List<Friends> friends = friendMapper.getAllFollowingByUid(uid);
        List<UserInfoVo> following = new ArrayList<>(friends.size());
        friends.forEach(item->{
            UserInfoVo userInfoVo = new UserInfoVo();
            userInfoVo.setUid(item.getToUid());
            following.add(userInfoVo);
        });
        return following;
    }

    private List<UserInfoVo> getFollowed(Long uid){
        List<Friends> friends = friendMapper.getAllFollowedByUid(uid);
        List<UserInfoVo> followed = new ArrayList<>(friends.size());
        friends.forEach(item->{
            UserInfoVo userInfoVo = new UserInfoVo();
            userInfoVo.setUid(item.getFromUid());
            followed.add(userInfoVo);
        });
        return followed;
    }

    private FriendShipVo getAllFriendState(Long uid, FriendState friendState){
        FriendShipVo friendShipVo = new FriendShipVo();
        List<Friends> friends = friendMapper.getAllFriendsByUid(uid);

        List<UserInfoVo> following = friends.stream().filter(o->o.getFromUid().equals(uid)).map(o->{
            UserInfoVo userInfoVo = new UserInfoVo();
            userInfoVo.setUid(o.getToUid());
            return userInfoVo;
        }).collect(Collectors.toList());

        Set<Long> followingSet = following.stream().map(UserInfoVo::getUid).collect(Collectors.toSet());

        List<UserInfoVo> bothway = new ArrayList<>();

        List<UserInfoVo> followed = friends.stream().filter(o->o.getToUid().equals(uid)).map(o->{
            UserInfoVo userInfoVo = new UserInfoVo();
            userInfoVo.setUid(o.getFromUid());
            if (followingSet.contains(o.getFromUid())){
                bothway.add(userInfoVo);
            }
            return userInfoVo;
        }).collect(Collectors.toList());

        friendShipVo.setBothWay(bothway);
        if (FriendState.BOTH_WAY == friendState){
            return friendShipVo;
        }

        friendShipVo.setFollowed(followed);
        friendShipVo.setFollowing(following);
        return friendShipVo;
    }
}
