package com.example.api;

import com.example.api.req.AddGroupReq;
import com.example.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(value = "群聊功能")
public interface GroupChatApi {

    @ApiOperation("发送群聊消息")
    @PostMapping(value = "/group-chat/send-message", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Boolean> sendMessage(@RequestBody @Validated AddGroupReq req);

    @ApiOperation("拉取群聊消息")
    @PostMapping(value = "/group-chat/pull-message", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Boolean> pullMessage(@RequestBody @Validated AddGroupReq req);
}
