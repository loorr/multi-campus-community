package com.example.core.service;


import com.example.api.req.AddAttitudeReq;
import com.example.api.req.AddEssayReq;
import com.example.api.req.GetAllEssayPageReq;
import com.example.model.entity.Essay;
import com.github.pagehelper.PageInfo;

public interface EssayService {
    PageInfo<Essay> getAllEssay(GetAllEssayPageReq req);

    Boolean addEssay(AddEssayReq req);

    Boolean addEssayAttitude(AddAttitudeReq req);
}
