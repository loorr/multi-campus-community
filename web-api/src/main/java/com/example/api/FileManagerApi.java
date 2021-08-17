package com.example.api;

import com.example.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "文件管理接口")
public interface FileManagerApi {
    @ApiOperation("文件上传")
    @PostMapping(value = "/file/add-file", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Boolean> addFile(@RequestBody MultipartFile file);

    @ApiOperation("批量文件上传")
    @PostMapping(value = "/file/add-many-files", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Boolean> addManyFiles(@RequestBody MultipartFile[] files);
}
