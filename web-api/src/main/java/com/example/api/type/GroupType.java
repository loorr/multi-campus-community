package com.example.api.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GroupType {
    /** 群聊 */
    GENERAL(0),
    /** 单聊, 相当于两个人一个群*/
    PERSONAL(1);

    private int type;
}
