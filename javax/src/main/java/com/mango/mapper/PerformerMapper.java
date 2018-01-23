package com.mango.mapper;

import com.mango.model.Performer;
import com.mango.model.Picture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PerformerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Performer record);

    int insertSelective(Performer record);

    Performer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Performer record);

    int updateByPrimaryKey(Performer record);

    List<Performer> findAll(@Param("userid") String userid);
}