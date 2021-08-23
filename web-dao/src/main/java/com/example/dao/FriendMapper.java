package com.example.dao;


import com.example.api.req.AddOrDeleteUserReq;
import com.example.model.entity.Friends;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface FriendMapper {
    @Insert("INSERT INTO `friends`(`to_uid`, `from_uid`) VALUES(#{toUid}, #{uid})")
    int addFriend(AddOrDeleteUserReq req);

    @Select("SELECT `to_uid`, `from_uid` FROM `friends` WHERE `to_uid`=#{toUid} AND `from_uid` = #{fromUid} ")
    Friends getFriendShip(@Param("toUid") Long toUid, @Param("fromUid") Long fromUid);

    @Delete("DELETE FROM `friends` WHERE `to_uid`=#{toUid} AND `from_uid` = #{fromUid} \"")
    int deleteFriendShip(@Param("toUid") Long toUid, @Param("fromUid") Long fromUid);

    @Select("SELECT * FROM `friends` WHERE `from_uid`=#{uid}")
    List<Friends> getAllFollowingByUid(@Param("uid") Long uid);

    @Select("SELECT * FROM `friends` WHERE `to_uid`=#{uid} ")
    List<Friends> getAllFollowedByUid(@Param("uid") Long uid);

    @Select("SELECT * FROM `friends` WHERE `to_uid`=#{uid} OR `from_uid`=#{uid}")
    List<Friends> getAllFriendsByUid(@Param("uid") Long uid);
}

