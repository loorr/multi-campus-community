package com.example.rest.controller;


import com.example.api.FileManagerApi;
import com.example.common.ErrorCode;
import com.example.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@RestController
public class FileManagerController implements FileManagerApi {

    // 上传文件路径
    @Value("${file.base.director}")
    private String fileBaseDirector;

    @Override
    public Response<Boolean> addFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get(fileBaseDirector + fileName);
        try {
            Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
           Response.getFail(ErrorCode.FILE_ERROE.getCode(), ErrorCode.FILE_ERROE.getMsg());
        }
        return Response.getOk(true);
    }

    /**
     * 需要使用post man测试
     * @param files  文件的key为files
     * @return
     */
    @Override
    public Response<Boolean> addManyFiles(@RequestParam("files") MultipartFile[] files) {
        if (files == null){
            return Response.getFail(ErrorCode.EMPTY_PARAM.getCode(), ErrorCode.EMPTY_PARAM.getMsg());
        }
        for (MultipartFile multipartFile : files) {
            addFile(multipartFile);
        }
        return Response.getOk(true);
    }



    // https://cloud.tencent.com/developer/article/1541199 大文件传输方案
}
