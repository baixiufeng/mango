package com.mango.mapper;

import com.mango.model.Comment;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> findAll(@Param("uid") String uid);
}