package com.example.api.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zjianfa
 */
@Data
@ApiModel
public class LoginReq {
    @ApiModelProperty(value = "10位的用户id")
    private Long uid;

    @ApiModelProperty(value = "email")
    private String email;

    @ApiModelProperty(value = "密码")
    @NotNull
    private String password;
}
