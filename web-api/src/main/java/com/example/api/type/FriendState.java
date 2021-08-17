package com.example.api.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zjianfa
 */

@Getter
@AllArgsConstructor
public enum FriendState {
    FOLLWOWING(0),
    FOLLOWED(1),
    BOTH_WAY(2);
    private int code;
}
