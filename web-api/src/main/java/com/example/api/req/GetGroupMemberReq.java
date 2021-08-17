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
public class GetGroupMemberReq extends BaseIdReq {

    @ApiModelProperty(value = "10位的group id")
    @NotNull
    private Long groupId;
}
