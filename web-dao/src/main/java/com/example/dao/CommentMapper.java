package com.example.dao;

import com.example.model.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CommentMapper {

    @Insert("INSERT INTO `comment`(`parent_id`, `essay_id`, `uid`, `content`, `type`) " +
            "VALUES(#{parentId}, #{essayId}, #{uid}, #{content}, #{type})")
    int addComment(Comment comment);

}
