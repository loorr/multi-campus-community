package com.example.common.exception;

import com.example.common.BaseError;
import lombok.Getter;

@Getter
public class AuthExcetption extends RuntimeException{
    private String code;
    private String msg;

    public AuthExcetption(BaseError e){
        this.code = e.getCode();
        this.msg = e.getMsg();
    }
}
