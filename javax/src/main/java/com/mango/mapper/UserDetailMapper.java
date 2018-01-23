package com.mango.mapper;

import com.mango.model.UserDetail;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface UserDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserDetail record);

    int insertSelective(UserDetail record);

    UserDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserDetail record);

    int updateByPrimaryKey(UserDetail record);

    List<UserDetail> findAll(@Param("filmName") String filmName);
}