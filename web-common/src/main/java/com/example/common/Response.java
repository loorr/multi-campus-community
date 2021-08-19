package com.example.common;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author zjianfa
 */
@Data
@ApiModel
@AllArgsConstructor
public class Response<T> {

    @ApiModelProperty(value = "返回码", example = "200")
    private Integer code;

    @ApiModelProperty(value = "返回码描述", example = "ok")
    private String message;

    @ApiModelProperty(value = "响应时间戳", example = "2020-08-12 14:37:11")
    private Date timestamp;

    @ApiModelProperty(value = "返回结果")
    private T data;

    public static <T> Response<T> getOk(T data){
        return new Response(BaseErrorCode.OPERATION_SUCCESS.getCode(), BaseErrorCode.OPERATION_SUCCESS.getMsg(), new Date(), data);
    }

    public static <T> Response<T> getOk(){
        return getOk(null);
    }

    public static <T> Response<T> getFail(){
        return getFail(ChatErrorCode.OPERATION_ERROR.getCode(), ChatErrorCode.OPERATION_ERROR.getMsg(),null);
    }

    public static <T> Response<T> getFail(int code, String message, T data){
        return new Response<>(code, message, new Date(), data);
    }

    public static <T> Response<T> getFail(int code, String message){
        return getFail(code, message,null);
    }

    public static <T> Response<T> getFail(BaseError e){
        return getFail(e.getCode(), e.getMsg(),null);
    }
}
