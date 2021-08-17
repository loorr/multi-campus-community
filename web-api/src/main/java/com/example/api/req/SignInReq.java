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
public class SignInReq {

    @ApiModelProperty(value = "nickname")
    @NotNull
    private String nickname;

    @ApiModelProperty(value = "email")
    @NotNull
    private String email;

    @ApiModelProperty(value = "password")
    @NotNull
    private String password;

    @ApiModelProperty(value = "code")
    private String code;
}
