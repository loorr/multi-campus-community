package com.example.api.req.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("需要登陆的请求，继承该接口")
public class BaseIdReq {
    @ApiModelProperty(value = "10位的用户id", hidden = true)
    private Long uid;
}
