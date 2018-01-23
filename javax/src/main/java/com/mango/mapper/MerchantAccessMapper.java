package com.mango.mapper;
import com.mango.model.MerchantAccess;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MerchantAccessMapper {
    int deleteByPrimaryKey(String id);

    int insert(MerchantAccess record);

    int insertSelective(MerchantAccess record);

    MerchantAccess selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MerchantAccess record);

    int updateByPrimaryKey(MerchantAccess record);

    List<MerchantAccess> findAll(@Param("uid") String uid);
}