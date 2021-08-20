package com.example.api.req;

import com.example.api.req.common.BaseIdReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel
@EqualsAndHashCode(callSuper = true)
public class DeleteEssayReq extends BaseIdReq {

    @ApiModelProperty(value = "essayId")
    private Long essayId;
}
