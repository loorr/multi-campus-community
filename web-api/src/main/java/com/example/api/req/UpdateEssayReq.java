package com.example.api.req;

import com.example.api.req.common.BaseIdReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "更新动态，改变动态公开性")
public class UpdateEssayReq extends BaseIdReq {

    @ApiModelProperty(value = "essayId")
    private int essayId;
}
