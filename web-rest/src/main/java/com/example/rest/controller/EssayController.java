package com.example.rest.controller;


import com.example.api.EssayApi;
import com.example.api.req.*;
import com.example.api.vo.CommentVo;
import com.example.api.vo.EssayVo;
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
    public Response<Boolean> updataEssayState(DeleteEssayReq req) {
        return null;
    }

    @Override
    public Response<PageInfo<EssayVo>> getAllEssay(GetAllEssayPageReq req) {
        PageInfo<EssayVo> pageInfo = essayService.getAllEssay(req);
        return Response.getOk(pageInfo);
    }

    @NeedToken
    @Override
    public Response<Boolean> addEssayComment(AddCommentReq req) {
        req.setUid(getLoginUid());
        try {
            essayService.addEssayComment(req);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Response.getOk(true);
    }

    @Override
    public Response<CommentVo> getEssayComment(GetCommentReq req) {
        return Response.getOk(essayService.getEssayComment(req));
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
