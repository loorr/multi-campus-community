package com.example.core.service;



import com.example.api.req.AddGroupReq;
import com.example.api.req.JoinOrQuitGroupReq;
import com.example.api.req.SearchGroupReq;
import com.example.api.vo.GroupInfoVo;

import java.util.List;

public interface GroupService {

    boolean  joinGroup(JoinOrQuitGroupReq req);

    boolean addGroup(AddGroupReq req);

    List<GroupInfoVo> searchGroup(SearchGroupReq req);
}
