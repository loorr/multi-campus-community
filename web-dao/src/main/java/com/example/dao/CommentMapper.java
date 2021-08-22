package com.example.dao;

import com.example.model.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CommentMapper {

    @Insert("INSERT INTO `comment`(`parent_id`, `essay_id`, `uid`, `content`, `type`) " +
            "VALUES(#{parentId}, #{essayId}, #{uid}, #{content}, #{type})")
    int addComment(Comment comment);

    @Select("SELECT * FROM `comment` WHERE essay_id=#{essayId} ORDER BY `db_create_time`")
    List<Comment> getCommentByEssayId(@Param("essayId") Long essayId);
}
