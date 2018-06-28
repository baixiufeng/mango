package com.mango.mapper;

import com.mango.model.UserResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserResourceMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserResource record);

    int insertSelective(UserResource record);

    UserResource selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserResource record);

    int updateByPrimaryKey(UserResource record);

    List<UserResource> findByUserId(@Param("uid") String uid);

    List<UserResource> findAll();
}