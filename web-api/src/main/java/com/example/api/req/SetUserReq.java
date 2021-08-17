package com.example.api.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zjianfa
 */
@Data
@ApiModel
public class SetUserReq {

    @ApiModelProperty("nickname")
    private String nickname;

    @ApiModelProperty("email")
    private String email;

}
