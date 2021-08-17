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
public class JoinOrQuitGroupReq extends BaseIdReq {

    @ApiModelProperty(value = "groupId")
    @NotNull
    private Long groupId;
}
