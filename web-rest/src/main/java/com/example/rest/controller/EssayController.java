package com.example.rest.controller;


import com.example.api.EssayApi;
import com.example.api.req.*;
import com.example.common.Response;
import com.example.api.common.ChatException;
import com.example.common.annotations.NeedToken;
import com.example.core.service.EssayService;
import com.example.model.entity.Essay;
import com.example.rest.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EssayController extends BaseController implements EssayApi {



    @Autowired
    private EssayService essayService;

    @NeedToken
    @Override
    public Response<Boolean> addEssay(AddEssayReq req) {
        req.setUid(getLoginUid());
        try{
            essayService.addEssay(req);
        }catch (ChatException e){
            return Response.getFail(e.getCode(), e.getMsg());
        }
        return Response.getOk(true);
    }

    @Override
    public Response<Boolean> deleteEssay(DeleteEssayReq req) {
        return null;
    }



    @Override
    public Response<Boolean> updataEssayState(AddOrDeleteUserReq req) {
        return null;
    }

    @Override
    public Response<PageInfo<Essay>> getAllEssay(GetAllEssayPageReq req) {
        PageInfo<Essay> pageInfo = essayService.getAllEssay(req);
        return Response.getOk(pageInfo);
    }

    @Override
    public Response<Boolean> addEssayComment(GetAllEssayPageReq req) {
        return null;
    }


    @NeedToken
    @Override
    public Response<Boolean> addEssayAttitude(AddAttitudeReq req) {
        req.setUid(getLoginUid());
        try {
            essayService.addEssayAttitude(req);
        }catch (ChatException e){
            return Response.getFail(e);
        }
        return Response.getOk(true);
    }
}
