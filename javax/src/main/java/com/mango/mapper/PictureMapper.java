package com.mango.mapper;

import com.mango.model.Picture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PictureMapper {
    int deleteByPrimaryKey(String id);

    int insert(Picture record);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);

    List<Picture> findAll(@Param("uid") String uid);

}