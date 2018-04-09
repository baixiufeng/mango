package com.mango.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mango.mapper.UserResourceMapper;
import com.mango.model.UserResource;
import com.mango.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserResourceService {
    
    @Autowired
    private UserResourceMapper mapper;

    public int insert(UserResource userResource){
        userResource.setId(UuidUtil.getUuid32());
        userResource.setCreatTime(new Date());
        int result= mapper.insert(userResource);
        return result;
    }
    public int update(UserResource userResource){
        userResource.setUpdateTime(new Date());
        int result= mapper.updateByPrimaryKeySelective(userResource);
        return result;
    }
    public int delete(String id){
        int result= mapper.deleteByPrimaryKey(id);
        return result;
    }
    public UserResource selectfindByid(String id){
        UserResource result= mapper.selectByPrimaryKey(id);
        return result;
    }
    //分页查询用户
    public PageInfo<UserResource> pageInfo(Integer  pageNo, Integer  pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<UserResource> userResourceList = mapper.findAll();
        PageInfo<UserResource> pagelist = new PageInfo<UserResource>(userResourceList);
        return pagelist;
    }
    
}
