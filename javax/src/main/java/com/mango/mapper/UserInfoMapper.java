package com.mango.mapper;

import com.mango.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<UserInfo> findAll();

    UserInfo findByPhoneOrEmail(@Param("email") String email,@Param("phone") String phone);

    UserInfo findByUserName(@Param("phone") String phone);
}