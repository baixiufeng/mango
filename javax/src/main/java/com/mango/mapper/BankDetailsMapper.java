package com.mango.mapper;

import com.mango.model.BankDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BankDetailsMapper {
    int deleteByPrimaryKey(String id);

    int insert(BankDetails record);

    int insertSelective(BankDetails record);

    BankDetails selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BankDetails record);

    int updateByPrimaryKey(BankDetails record);

    List<BankDetails> findAll(@Param("uid") String uid);
}