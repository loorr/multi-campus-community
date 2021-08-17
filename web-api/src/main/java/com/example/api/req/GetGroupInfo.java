package com.example.api.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zjianfa
 */
@Data
@ApiModel
public class GetGroupInfo {
    @ApiModelProperty(value = "10位的group id")
    @NotNull
    private Long groupId;
}
