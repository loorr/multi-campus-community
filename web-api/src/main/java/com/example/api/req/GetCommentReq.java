package com.example.api.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class GetCommentReq {
    @ApiModelProperty(value = "动态id")
    @NotNull
    private Long essayId;
}
