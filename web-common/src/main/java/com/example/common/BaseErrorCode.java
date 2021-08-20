package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum BaseErrorCode implements BaseError{
    SYSTEM_BUSY("001","系统繁忙"),
    OPERATION_SUCCESS("000", "操作成功"),
    ILLEGAL_PARAMETERS("200","参数不合法"),
    ;

    private final String code;
    private final String msg;
}
