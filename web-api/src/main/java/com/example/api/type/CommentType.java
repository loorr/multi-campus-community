package com.example.api.type;

import com.example.model.entity.Comment;
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

    public static boolean getType(CommentType type){
        return type == REPLY_COMMENT;
    }

    public static CommentType getCommentType(boolean type){
        if (type){
            return REPLY_COMMENT;
        }
        return REPLY_ESSAY;
    }
}
