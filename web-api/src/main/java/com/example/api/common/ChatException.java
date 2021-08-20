package com.example.api.common;


import com.example.common.BaseError;
import com.example.common.BaseErrorCode;
import lombok.Data;

/**
 * @author zjianfa
 */
@Data
public class ChatException extends RuntimeException implements BaseError {
    private String code;
    private String msg;

    public ChatException(BaseError e){
        this.code = e.getCode();
        this.msg = e.getMsg();
    }


}
