package com.example.rest.controller;

import com.example.api.GroupApi;
import com.example.api.req.*;
import com.example.api.vo.GroupInfoVo;
import com.example.api.vo.GroupMemberVo;
import com.example.common.Response;
import com.example.core.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zjianfa
 */
@RestController
public class GroupController implements GroupApi {
    @Autowired
    GroupService groupService;

    @Override
    public Response<Boolean> addGroup(AddGroupReq req) {
        boolean result = groupService.addGroup(req);
        return Response.getOk(result);
    }

    @Override
    public Response<Boolean> joinGroup(JoinOrQuitGroupReq req) {
        boolean result = groupService.joinGroup(req);
        return Response.getOk(result);
    }

    @Override
    public Response<Boolean> quitGroup(JoinOrQuitGroupReq req) {
        return null;
    }

    @Override
    public Response<List<GroupInfoVo>> searchGroup(SearchGroupReq req) {
        List<GroupInfoVo> list = groupService.searchGroup(req);
        return Response.getOk(list);
    }

    @Override
    public Response<GroupMemberVo> getGroupMember(GetGroupMemberReq req) {
        return null;
    }

    @Override
    public Response<GroupInfoVo> getGroupInfo(GetGroupMemberReq req) {
        return null;
    }

    @Override
    public Response<Boolean> updateGroupInfo(UpdateGroupNameReq req) {
        return null;
    }
}
