package com.example.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface GroupMemberMapper {

    @Insert("INSERT INTO `group_member`(`group_id`, `uid`) VALUES (#{groupId}, #{uid})")
    int addMember(@Param("groupId") Long groupId, @Param("uid") Long uid);

    @Insert("DELETE FROM `group_member` WHERE `group_id` = #{groupId} AND `uid` = #{uid}")
    int removeMember(@Param("groupId") Long groupId, @Param("uid") Long uid);
}
