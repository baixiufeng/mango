package com.mango.mapper;


import com.mango.model.Contacts;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ContactsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Contacts record);

    int insertSelective(Contacts record);

    Contacts selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Contacts record);

    int updateByPrimaryKey(Contacts record);

    List<Contacts> findAll(@Param("uid") String uid);
}