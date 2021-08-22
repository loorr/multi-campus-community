package com.example.core.service.impl;


import com.example.api.req.*;
import com.example.api.common.ChatErrorCode;
import com.example.api.common.ChatException;
import com.example.api.type.CommentType;
import com.example.api.vo.CommentItem;
import com.example.api.vo.CommentVo;
import com.example.api.vo.EssayVo;
import com.example.core.common.RedisEssayService;
import com.example.core.service.EssayService;
import com.example.dao.CommentMapper;
import com.example.dao.EssayMapper;
import com.example.model.entity.Comment;
import com.example.model.entity.Essay;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class EssayServiceImpl implements EssayService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private EssayMapper essayMapper;

    @Resource
    private RedisEssayService redisEssayService;

    @Override
    public PageInfo<EssayVo> getAllEssay(GetAllEssayPageReq req) {
        // 查询
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<Essay> list = essayMapper.getAllEssayPage(req);
        // 将查询结果给 pageInfo 处理
        PageInfo<Essay> pageInfo = new PageInfo<>(list);
        return new PageInfo<EssayVo>();
    }

    @Override
    public Boolean addEssay(AddEssayReq req) {
        if (req.getHasSecret()){
            Long srcUid = req.getUid();
            // TODO 下一步考虑如何设计加密算法
            req.setUid(srcUid*10);
        }
        try{
            int row = essayMapper.insertEssay(req);
            return row == 1;
        }catch (Exception e){
            throw new ChatException(ChatErrorCode.ESSAY_FILED);
        }
    }

    @Override
    public Boolean addEssayAttitude(AddAttitudeReq req) {
        switch (req.getAttitudeType()){
            case LIKE:
                redisEssayService.setLike(req.getEssayId(), req.getUid());
                return true;
            case DISLIKE:
                redisEssayService.setDislike(req.getEssayId(), req.getUid());
                return true;
            case CANCEL_LIKE:
                redisEssayService.cancelLike(req.getEssayId(), req.getUid());
                return true;
            case CANCEL_DISLIKE:
                redisEssayService.cancelDislike(req.getEssayId(), req.getUid());
                return true;
            default:
                return false;
        }
    }

    @Override
    public Boolean deleteEssay(DeleteEssayReq req) {
        Essay essay = essayMapper.getEssayByEssayIdAndUid(req.getEssayId(), req.getUid());
        if (essay == null){
            throw new ChatException(ChatErrorCode.NOT_ESSAY_PERMISSION);
        }
        essay.setHasDelete(true);
        try {
            essayMapper.updateEssay(essay);
        }catch (DuplicateKeyException e){
            throw new ChatException(ChatErrorCode.DUPLICATE_ERROR);
        }
        return true;
    }

    @Override
    public Boolean updataEssayState(DeleteEssayReq req) {
        Essay essay = essayMapper.getEssayByEssayIdAndUid(req.getEssayId(), req.getUid());
        if (essay == null){
            throw new ChatException(ChatErrorCode.NOT_ESSAY_PERMISSION);
        }
        if (essay.getHasSecret()){
            throw new ChatException(ChatErrorCode.SECRET_CANNOT_PPUBLICK);
        }
        if (!essay.getCanPublic()){
            throw new ChatException(ChatErrorCode.HAS_PRIVATE);
        }
        essay.setCanPublic(false);
        try {
            essayMapper.updateEssay(essay);
        }catch (DuplicateKeyException e){
            throw new ChatException(ChatErrorCode.DUPLICATE_ERROR);
        }
        return true;
    }



    @Override
    public Boolean addEssayComment(AddCommentReq req) {
        Comment comment = new Comment();
        comment.setParentId(req.getParentId());
        comment.setEssayId(req.getEssayId());

        Boolean hasSecret = essayMapper.getHasSecretByEssayId(req.getEssayId());
        if (hasSecret == null){
            throw new ChatException(ChatErrorCode.OPERATION_ERROR);
        }
        if (hasSecret){
            comment.setUid(11L);
        }else {
            comment.setUid(req.getUid());
        }
        comment.setContent(req.getContent());
        comment.setType(CommentType.getType(req.getCommentType()));
        try {
            int i = commentMapper.addComment(comment);
            if (i == 1){
                return true;
            }
        }catch (DuplicateKeyException e){
            throw new ChatException(ChatErrorCode.DUPLICATE_ERROR);
        }
        return false;
    }

    @Override
    public CommentVo getEssayComment(GetCommentReq req) {
        List<Comment> commentList = commentMapper.getCommentByEssayId(req.getEssayId());
        if (commentList == null){
            return new CommentVo();
        }
        CommentVo commentVo = new CommentVo(req.getEssayId());
        commentList.forEach(item->{
            Map<Integer, CommentItem> map = commentVo.getCommentItemMap();
            CommentItem firstLevelItem = map.getOrDefault(item.getParentId(), new CommentItem());
            // 如果是评论的评论
            if(item.getType()){
                CommentItem commentItem = new CommentItem();
                BeanUtils.copyProperties(item, commentItem);
                firstLevelItem.getCommentItemList().add(commentItem);
            }else{
                BeanUtils.copyProperties(item, firstLevelItem);
            }
            map.put(item.getParentId(), firstLevelItem);
        });
        return commentVo;
    }
}
