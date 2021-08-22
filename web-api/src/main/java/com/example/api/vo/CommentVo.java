package com.example.api.vo;

import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Data
public class CommentVo {
    private Long essayId;
    // 一级评论
    private Map<Integer, CommentItem> commentItemMap = new HashMap<>();

    public CommentVo(Long essayId) {
        this.essayId = essayId;
    }
}

