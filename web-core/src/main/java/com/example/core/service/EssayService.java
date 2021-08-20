package com.example.core.service;


import com.example.api.req.*;
import com.example.api.vo.EssayVo;
import com.github.pagehelper.PageInfo;

public interface EssayService {
    PageInfo<EssayVo> getAllEssay(GetAllEssayPageReq req);

    Boolean addEssay(AddEssayReq req);

    Boolean addEssayAttitude(AddAttitudeReq req);

    Boolean deleteEssay(DeleteEssayReq req);

    Boolean updataEssayState(DeleteEssayReq req);

    Boolean addEssayComment(AddCommentReq req);
}
