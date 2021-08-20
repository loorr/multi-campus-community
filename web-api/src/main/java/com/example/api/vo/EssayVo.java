package com.example.api.vo;

import lombok.Data;

@Data
public class EssayVo {
    /** 发表人uid */
    private Long uid;
    private String content;
    private String topic;
    private Long original;
    private int lickNum;
    private int dislikeNum;
    private Boolean hasSecret;
    private Boolean canPublic;

    private Boolean hasLike;
    private Boolean hasDislike;
}
