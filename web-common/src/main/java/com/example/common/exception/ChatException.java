package com.example.common.exception;

import com.example.common.ChatErrorCode;
import lombok.Data;

/**
 * @author zjianfa
 */
@Data
public class ChatException extends RuntimeException{
    private int code;
    private String msg;

    public ChatException(ChatErrorCode chatErrorCode){
        this.code = chatErrorCode.getCode();
        this.msg = chatErrorCode.getMsg();
    }
}
