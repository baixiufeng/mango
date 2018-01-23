package com.mango.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mango.mapper.BankDetailsMapper;
import com.mango.model.BankDetails;
import com.mango.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author baixiufeng
 * @Description:银行卡管理类服务层
 * @Date: Created in 10:21 10:21
 */
@Service
public class BankDetailsService {
    @Autowired
    private BankDetailsMapper bankDetailsMapper;
    public int insert(BankDetails bankDetails){
        bankDetails.setId(UuidUtil.getUuid32());
        bankDetails.setCreateTime(new Date());
        int result= bankDetailsMapper.insert(bankDetails);
        return result;
    }
    public int update(BankDetails bankDetails){
        bankDetails.setUpdateTime(new Date());
        int result= bankDetailsMapper.updateByPrimaryKeySelective(bankDetails);
        return result;
    }
    public int delete(String id){
        int result= bankDetailsMapper.deleteByPrimaryKey(id);
        return result;
    }
    public BankDetails selectfindByid(String id){
        BankDetails result= bankDetailsMapper.selectByPrimaryKey(id);
        return result;
    }
    //分页查询用户
    public PageInfo<BankDetails> pageInfo(Integer  pageNo, Integer  pageSize,String uid){
        PageHelper.startPage(pageNo,pageSize);
        List<BankDetails> bankDetailsList = bankDetailsMapper.findAll(uid);
        PageInfo<BankDetails> pagelist = new PageInfo<BankDetails>(bankDetailsList);
        return pagelist;
    }
}
