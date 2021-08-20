package com.example.api.common;

import com.example.common.BaseError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author zjianfa
 */


@Getter
@ToString
@AllArgsConstructor
public enum ChatErrorCode implements BaseError {
    DUPLICATE_ERROR("001", "信息重复"),
    EMAIL_SEND_FILED("002", "邮件发送失败"),
    ESSAY_FILED("003","动态发表失败"),
    CODE_SEND_FILED("004","验证码发送失败"),
    CODE_EXPRIED("005", "验证码已过期"),
    NO_CODE("003", "验证码缺失"),
    ERROR_CODE("003", "验证码错误"),
    OPERATION_ERROR("400", "操作失败"),
    PASSWORD_ERROR("100", "密码错误"),
    EMPTY_PARAM("102","参数为空"),
    FILE_ERROE("1003", "文件错误"),
    AUTH_ERROR("101", "用户信息错误");


    private final String code;
    private final String msg;
}
