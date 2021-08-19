package com.example.core.service.impl;


import com.example.api.req.AddEssayReq;
import com.example.api.req.GetAllEssayPageReq;
import com.example.common.ChatErrorCode;
import com.example.common.exception.ChatException;
import com.example.core.service.EssayService;
import com.example.dao.EssayMapper;
import com.example.model.entity.Essay;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EssayServiceImpl implements EssayService {

    @Autowired
    private EssayMapper essayMapper;

    @Override
    public PageInfo<Essay> getAllEssay(GetAllEssayPageReq req) {
        // 查询
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<Essay> list = essayMapper.getAllEssayPage(req);
        // 将查询结果给 pageInfo 处理
        PageInfo<Essay> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Boolean addEssay(AddEssayReq req) {
        try{
            int row = essayMapper.insertEssay(req);
            return row == 1;
        }catch (Exception e){
            throw new ChatException(ChatErrorCode.ESSAY_FILED);
        }
    }
}
