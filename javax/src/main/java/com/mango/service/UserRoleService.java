package com.mango.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mango.mapper.UserRoleMapper;
import com.mango.model.UserDetail;
import com.mango.model.UserRole;
import com.mango.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleMapper mapper;


    public int insert(UserRole userRole){
        userRole.setId(UuidUtil.getUuid32());
        userRole.setCreateTime(new Date());
        int result= mapper.insert(userRole);
        return result;
    }

    public int update(UserRole userRole){
        userRole.setUpdateTime(new Date());
        int result= mapper.updateByPrimaryKeySelective(userRole);
        return result;
    }

    public int delete(String id){
        int result= mapper.deleteByPrimaryKey(id);
        return result;
    }
    public UserRole selectfindByid(String id){
        UserRole result= mapper.selectByPrimaryKey(id);
        return result;
    }


    //分页查询用户
    public PageInfo<UserRole> pageInfo(Integer  pageNo, Integer  pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<UserRole> userRoleList = mapper.findAll();
        PageInfo<UserRole> pagelist = new PageInfo<UserRole>(userRoleList);
        return pagelist;
    }
}
