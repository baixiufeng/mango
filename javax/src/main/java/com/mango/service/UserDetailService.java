package com.mango.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mango.mapper.UserDetailMapper;
import com.mango.model.UserDetail;
import com.mango.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author baixiufeng
 * @Description:
 * @Date: Created in 15:51 15:51
 */
@Service
public class UserDetailService {
    @Autowired
    private UserDetailMapper mapper;

    public int insert(UserDetail userDetail){
        userDetail.setId(UuidUtil.getUuid32());
        userDetail.setCreateTime(new Date());
        int result= mapper.insert(userDetail);
        return result;
    }
    public int update(UserDetail userDetail){
        userDetail.setUpdateTime(new Date());
        int result= mapper.updateByPrimaryKeySelective(userDetail);
        return result;
    }
    public int delete(String id){
        int result= mapper.deleteByPrimaryKey(id);
        return result;
    }
    public UserDetail selectfindByid(String id){
        UserDetail result= mapper.selectByPrimaryKey(id);
        return result;
    }
    //分页查询用户
    public PageInfo<UserDetail> pageInfo(Integer  pageNo, Integer  pageSize, String filmName){
        PageHelper.startPage(pageNo,pageSize);
        List<UserDetail> uerDetailList = mapper.findAll(filmName);
        PageInfo<UserDetail> pagelist = new PageInfo<UserDetail>(uerDetailList);
        return pagelist;
    }
}
