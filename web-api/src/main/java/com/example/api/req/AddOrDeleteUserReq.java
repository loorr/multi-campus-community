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
public class AddOrDeleteUserReq {

    @ApiModelProperty(hidden = true)
    private Long fromUid;

    @ApiModelProperty("toUid")
    @NotNull
    private Long toUid;
}
