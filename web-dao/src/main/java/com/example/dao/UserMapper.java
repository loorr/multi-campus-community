package com.example.dao;


import com.example.model.entity.User;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zjianfa
 */
@Mapper
public interface UserMapper {

    @Select("select * from user ORDER BY `id` limit #{num} ")
    List<User> getAllUser(@Param("num") int num);

    @Select("<script>"
                + "SELECT * "
                + "FROM `user` "
                + "<where> "
                    +"<if test='email != null and email !=\"\"'>and email = #{email} </if>"
                    +"<if test='uid != null'> and uid = #{uid} </if>"
                + "</where>"
            + "</script>"
    )
    User getUserByEmailOrUid(@Param("email") String email, @Param("uid") Long uid);


    @Insert("INSERT INTO `user`(uid, nickname, email, phone, password, admin) " +
            "values(#{uid}, #{nickname}, #{email}, #{phone}, #{password}, #{admin})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()",
                keyProperty = "uid",
                resultType = Long.class,
                before = false)
    int insertOne(User user);

    @Update("UPDATE `user` SET nickname=#{nickname}, email=#{email}, phone=#{phone}, " +
            "password=#{password},is_admin=#{isAdmin} " +
            "WHERE uid=#{uid}")
    int updateOne(User user);

    @Delete("DELETE FROM `user` WHERE uid = #{uid}")
    int deleteOne(@Param("uid") Long uid);

}
