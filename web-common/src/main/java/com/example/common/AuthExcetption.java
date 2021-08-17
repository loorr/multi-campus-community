package com.example.common;

import lombok.Getter;

@Getter
public class AuthExcetption extends RuntimeException{
    private int code;
    private String msg;

    public AuthExcetption(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }
}
