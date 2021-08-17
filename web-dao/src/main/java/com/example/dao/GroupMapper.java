package com.example.dao;


import com.example.model.entity.Group;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface GroupMapper {

    @Update("UPDATE `group` SET `curr_num` = `curr_num` + 1 WHERE `group_id` = #{groupId} ")
    int addGroupCurrNum(@Param("groupId") Long groupId);

    @Delete("UPDATE `group` SET `curr_num` = `curr_num` - 1 WHERE `group_id` = #{groupId}")
    int subGroupCurrNum(@Param("groupId") Long groupId);

    @Update("")
    int updateGroupInfo(Group group);

    @Insert("INSERT INTO `group`(`group_id`, `leader_id`, `type`, `group_name`, `curr_num`) " +
            "VALUES(#{groupId}, #{leaderId}, #{type}, #{groupName}, #{currNum})")
    int addGroup(Group group);

    @Select("SELECT * FROM `group` WHERE `group_id` = #{groupId}")
    List<Group> getAllGroupList(@Param("groupId")Long groupId);



}
