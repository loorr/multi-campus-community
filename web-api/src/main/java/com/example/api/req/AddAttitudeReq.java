package com.example.api.req;

import com.example.api.req.common.BaseIdReq;
import com.example.api.type.AttitudeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class AddAttitudeReq extends BaseIdReq {
    @ApiModelProperty(value = "动态id")
    @NotNull
    private Long essayId;

    @ApiModelProperty(value = "类型，点赞或者不喜欢")
    @NotNull
    private AttitudeType attitudeType;
}
