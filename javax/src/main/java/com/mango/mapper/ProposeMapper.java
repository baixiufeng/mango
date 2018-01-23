package com.mango.mapper;

import com.mango.model.Propose;

public interface ProposeMapper {
    int deleteByPrimaryKey(String id);

    int insert(Propose record);

    int insertSelective(Propose record);

    Propose selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Propose record);

    int updateByPrimaryKey(Propose record);

}