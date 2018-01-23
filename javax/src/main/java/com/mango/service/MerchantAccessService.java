package com.mango.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mango.mapper.MerchantAccessMapper;
import com.mango.model.MerchantAccess;
import com.mango.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;


@Service
public class MerchantAccessService {
    @Autowired
    private MerchantAccessMapper merchantAccessMapper;
    public int insert(MerchantAccess merchantAccess){
        merchantAccess.setId(UuidUtil.getUuid32());
        merchantAccess.setCreateTime(new Date());
        int result= merchantAccessMapper.insert(merchantAccess);
        return result;
    }
    public int update(MerchantAccess merchantAccess){
        merchantAccess.setUpdateTime(new Date());
        int result= merchantAccessMapper.updateByPrimaryKeySelective(merchantAccess);
        return result;
    }
    public int delete(String id){
        int result= merchantAccessMapper.deleteByPrimaryKey(id);
        return result;
    }
    public MerchantAccess selectfindByid(String id){
        MerchantAccess result= merchantAccessMapper.selectByPrimaryKey(id);
        return result;
    }
    //分页查询用户
    public PageInfo<MerchantAccess> pageInfo(Integer  pageNo, Integer  pageSize, String filmName){
        PageHelper.startPage(pageNo,pageSize);
        List<MerchantAccess> merchantAccessList = merchantAccessMapper.findAll(filmName);
        PageInfo<MerchantAccess> pagelist = new PageInfo<MerchantAccess>(merchantAccessList);
        return pagelist;
    }
}