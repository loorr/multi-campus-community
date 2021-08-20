package com.example.api.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zjianfa
 */

@Getter
@AllArgsConstructor
public enum FriendState {
    /** 关注的人 */
    FOLLWOWING(0),
    /** 被关注 */
    FOLLOWED(1),
    /** 好友 */
    BOTH_WAY(2);
    private int code;
}
