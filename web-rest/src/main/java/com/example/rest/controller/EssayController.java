package com.example.rest.controller;


import com.example.api.EssayApi;
import com.example.api.req.AddEssayReq;
import com.example.api.req.AddOrDeleteUserReq;
import com.example.api.req.DeleteEssayReq;
import com.example.api.req.GetAllEssayPageReq;
import com.example.common.Response;
import com.example.common.exception.ChatException;
import com.example.core.service.EssayService;
import com.example.model.entity.Essay;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EssayController implements EssayApi {

    @Autowired
    private EssayService essayService;

    @Override
    public Response<Boolean> addEssay(AddEssayReq req) {
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
}
