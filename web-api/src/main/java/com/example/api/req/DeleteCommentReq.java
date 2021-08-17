package com.example.api.req;

import com.example.api.req.common.BaseIdReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class DeleteCommentReq extends BaseIdReq {

    @ApiModelProperty(value = "评论id")
    private int commentId;
}
