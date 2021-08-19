package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum BaseErrorCode implements BaseError{
    OPERATION_SUCCESS(000, "操作成功");
    private final Integer code;
    private final String msg;
}
