package com.example.dao;

import com.example.api.req.AddEssayReq;
import com.example.api.req.GetAllEssayPageReq;
import com.example.model.entity.Essay;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface EssayMapper {

    @Select("SELECT * FROM essay order by id")
    List<Essay> getAllEssayPage(GetAllEssayPageReq req);

    @Select("")
    boolean getHasSecretByEssayId(@Param("essayId") Long essayId);

    @Insert("<script>" +
            "INSERT INTO `essay`(`uid`, `content`, `topic`, " +
            "<if test='original !=null and original!=\"\"'>original, </if>" +
            "`has_secret`, `can_public` " +
            ") " +
            "VALUES(" +
            "#{uid}, #{content}, #{topic}," +
            "<if test='original !=null and original!=\"\"'> #{original}, </if>" +
            "#{hasSecret}, #{canPublic}"+
            ")" +
            "</script>")
    int insertEssay(AddEssayReq req);

    @Select("<script>" +
            "SELECT * " +
            "FROM `essay` " +
            "WHERE `uid` = #{uid} " +
            "AND has_delete = false " +
            "<if test='hasSecret != null'>AND has_secret = #{hasSecret} </if>" +
            "<if test='can_public != null'> AND can_public = #{canPublic} </if> " +
            "<if test='original != null'> AND original = #{original} </if> " +
            "<if test='topic != null and topic != \"\"'>AND topic = #{topic} </if>" +
            "order by `db_create_time`" +
            "</script>")
    List<Essay> getOwnerEssay();

    @Select("SELECT * FROM `essay` WHERE `uid`=#{uid} AND `id`=#{essayId} AND `has_delete`=false")
    Essay getEssayByEssayIdAndUid(@Param("essayId") Long essayId, @Param("uid") Long uid);

    @Update("<script> " +
            "UPDATE " +
            "</script>")
    int updateEssay(Essay essay);

}
