package com.example.api.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommentType {
    /** 回复动态 */
    REPLY_ESSAY(0),
    /** 回复评论 */
    REPLY_COMMENT(1),
    ;

    private int code;
}
