package com.example.api.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommentType {
    REPLY_ESSAY(0),
    REPLY_COMMENT(1),
    ;

    private int code;
}
