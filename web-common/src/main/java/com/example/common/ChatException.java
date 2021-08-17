package com.example.common;

import lombok.Data;

/**
 * @author zjianfa
 */
@Data
public class ChatException extends RuntimeException{
    private int code;
    private String msg;

    public ChatException(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }
}
