package com.example.common.exception;

import com.example.common.ChatErrorCode;
import lombok.Getter;

@Getter
public class AuthExcetption extends RuntimeException{
    private int code;
    private String msg;

    public AuthExcetption(ChatErrorCode chatErrorCode){
        this.code = chatErrorCode.getCode();
        this.msg = chatErrorCode.getMsg();
    }
}
