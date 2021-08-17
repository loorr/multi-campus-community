package com.example.api.req;

import com.example.api.req.common.BaseIdReq;
import com.example.api.type.FriendState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zjianfa
 */
@Data
@ApiModel
public class GetAllFriendReq extends BaseIdReq {

    @ApiModelProperty(value = "标识好友范围，关注者，粉丝，好友")
    @NotNull
    private FriendState range;
}
