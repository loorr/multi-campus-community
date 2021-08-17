package com.example.model.entity;

import lombok.Data;

@Data
public class Comment extends BaseModel{
    private int parentId;
    private Long essayId;
    private Long uid;
    private String content;
    private int likeNum;
    private int dislikeNum;
    private boolean hasDelete;
}
