package com.example.model.entity;

import lombok.Data;

@Data
public class Essay extends BaseModel{

    private Long uid;
    private String content;
    private String topic;
    private Long original;
    private int lickNum;
    private int dislikeNum;
    private Boolean hasSecret;
    private Boolean canPublic;
    private Boolean hasDelete;

}
