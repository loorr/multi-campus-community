package com.example.core.service.impl;


import com.example.api.req.AddGroupReq;
import com.example.api.req.JoinOrQuitGroupReq;
import com.example.api.req.SearchGroupReq;
import com.example.api.vo.GroupInfoVo;
import com.example.common.ChatErrorCode;
import com.example.common.IdGenerator;
import com.example.common.exception.ChatException;
import com.example.core.service.GroupService;
import com.example.dao.GroupMapper;
import com.example.dao.GroupMemberMapper;
import com.example.model.entity.Group;
import com.example.model.type.GroupType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupMapper groupMapper;

    @Autowired
    GroupMemberMapper groupMemberMapper;

    @Override
    @Transactional
    public boolean joinGroup(JoinOrQuitGroupReq req) {
        try{
            groupMapper.addGroupCurrNum(req.getGroupId());
            groupMemberMapper.addMember(req.getGroupId(), req.getUid());
        }catch (DuplicateKeyException e){
            throw new ChatException(ChatErrorCode.DUPLICATE_ERROR);
        }
        return true;
    }

    @Override
    public boolean addGroup(AddGroupReq req) {
        Group group = new Group();
        group.setGroupId(IdGenerator.getUid());
        // TODO
        group.setType(GroupType.PERSONAL);
        group.setGroupName(req.getGroupName());
        group.setCurrNum(1);
        group.setLeaderId(req.getUid());

        try{
            groupMapper.addGroup(group);
        }catch (DuplicateKeyException e){
            throw new ChatException(ChatErrorCode.DUPLICATE_ERROR);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<GroupInfoVo> searchGroup(SearchGroupReq req) {
        List<Group> groupList = groupMapper.getAllGroupList(req.getGroupId());
        if (groupList == null || groupList.isEmpty()){
            return new ArrayList<>();
        }
        List<GroupInfoVo> groupInfoVoList = groupList.stream().map(o->{
            GroupInfoVo groupInfoVo = new GroupInfoVo();
            BeanUtils.copyProperties(o, groupInfoVo);
            return groupInfoVo;
        }).collect(Collectors.toList());
        return groupInfoVoList;
    }
}
