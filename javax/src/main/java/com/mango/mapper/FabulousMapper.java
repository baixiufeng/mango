package com.mango.mapper;

import com.mango.model.Fabulous;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface FabulousMapper {
    int deleteByPrimaryKey(String id);

    int insert(Fabulous record);

    int insertSelective(Fabulous record);

    Fabulous selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Fabulous record);

    int updateByPrimaryKey(Fabulous record);

    List<Fabulous> findAll(@Param("uid") String uid);
}