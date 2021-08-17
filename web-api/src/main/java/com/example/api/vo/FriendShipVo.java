package com.example.api.vo;

import lombok.Data;

import java.util.List;

/**
 * @author zjianfa
 */
@Data
public class FriendShipVo {

    private Long uid;
    private List<UserInfoVo> following;
    private List<UserInfoVo> followed;
    private List<UserInfoVo> bothWay;

}
