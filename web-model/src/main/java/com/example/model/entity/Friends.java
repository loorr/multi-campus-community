package com.example.model.entity;

import lombok.Data;

/**
 * @author zjianfa
 */
@Data
public class Friends extends BaseModel{

    private Long toUid;
    private Long fromUid;

}
