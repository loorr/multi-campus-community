package com.example.api.req;


import com.example.api.req.common.BaseIdReq;
import com.example.api.type.CommentType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@ApiModel
public class AddCommentReq extends BaseIdReq {

    @ApiModelProperty(value = "父评论id")
    private int parentId;

    @ApiModelProperty(value = "评论类型")
    private CommentType commentType;

    @ApiModelProperty(value = "动态id")
    @NotNull
    private Long essayId;

    @ApiModelProperty("内容")
    @NotEmpty
    private String content;
}
