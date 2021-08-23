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
    FOLLOWING(0),
    /** 被关注 */
    FOLLOWED(1),
    /** 好友 */
    BOTH_WAY(2),
    /** 所有 */
    ALL(3);
    private int code;
}
