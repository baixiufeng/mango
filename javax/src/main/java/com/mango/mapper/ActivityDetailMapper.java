package com.mango.mapper;

import com.mango.model.ActivityDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(ActivityDetail record);

    int insertSelective(ActivityDetail record);

    ActivityDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ActivityDetail record);

    int updateByPrimaryKey(ActivityDetail record);

    List<ActivityDetail> findAll(@Param("author") String author);
}