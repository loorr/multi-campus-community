package com.example.api;

import com.example.api.req.*;
import com.example.common.Response;
import com.example.model.entity.Essay;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author zjianfa
 */
@Api(value = "个人动态，包括匿名,实名 接口")
public interface EssayApi {

    @ApiOperation("发布动态, 包括匿名和实名, 转发动态")
    @PostMapping(value = "/essay/add-essay", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Boolean> addEssay(@RequestBody @Validated AddEssayReq req);

    @ApiOperation("删除动态")
    @PostMapping(value = "/essay/delete-essay", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Boolean> deleteEssay(@RequestBody @Validated DeleteEssayReq req);

    @ApiOperation("改变动态公开性")
    @PostMapping(value = "/essay/update-essay-state", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Boolean> updataEssayState(@RequestBody @Validated AddOrDeleteUserReq req);

    @ApiOperation("获取公开的动态")
    @PostMapping(value = "/essay/get-all-essay", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<PageInfo<Essay>> getAllEssay(@RequestBody @Validated GetAllEssayPageReq req);

    @ApiOperation("添加评论")
    @PostMapping(value = "/essay/add-essay-comment", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Boolean> addEssayComment(@RequestBody @Validated GetAllEssayPageReq req);

    @ApiOperation("点赞 或者 不喜欢")
    @PostMapping(value = "/essay/add-attitude", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Boolean> addEssayAttitude(@RequestBody @Validated AddAttitudeReq req);
}
