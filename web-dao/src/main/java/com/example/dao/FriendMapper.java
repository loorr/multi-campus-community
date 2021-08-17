package com.example.dao;


import com.example.api.req.AddOrDeleteUserReq;
import com.example.model.entity.Friends;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface FriendMapper {
    @Insert("INSERT INTO `friends`(`to_uid`, `from_uid`) VALUES(#{starUid}, #{uid})")
    int addFriend(AddOrDeleteUserReq req);

    @Select("SELECT `to_uid`, `from_uid` FROM `friends` WHERE `to_uid`=#{toUid} AND `from_uid` = #{fromUid} ")
    Friends getFriendShip(@Param("toUid") Long toUid, @Param("fromUid") Long fromUid);

    @Delete("DELETE FROM `friends` WHERE `to_uid`=#{toUid} AND `from_uid` = #{fromUid} \"")
    int deletFriendShip(@Param("toUid") Long toUid, @Param("fromUid") Long fromUid);
}

