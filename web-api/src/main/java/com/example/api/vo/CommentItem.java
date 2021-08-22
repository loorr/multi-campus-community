package com.example.api.vo;

import lombok.Data;

import java.util.List;

@Data
public class CommentItem {
    private Integer id;
    private Integer parentId;
    private Long uid;
    private String content;
    private Integer likeNum;
    private Integer dislikeNum;
    // 评论的评论
    private List<CommentItem> commentItemList;
}
