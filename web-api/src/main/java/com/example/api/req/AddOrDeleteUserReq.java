package com.example.api.req;

import com.example.api.req.common.BaseIdReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zjianfa
 */
@Data
@ApiModel
public class AddOrDeleteUserReq extends BaseIdReq {
    @ApiModelProperty("toUid")
    @NotNull
    private Long toUid;
}
