package com.example.model.entity;

import lombok.Data;

/**
 * @author zjianfa
 */
@Data
public class User extends BaseModel{

    private Long uid;
    private String nickname;
    private String email;
    private Long phone;
    private String password;
    private boolean admin;

}
