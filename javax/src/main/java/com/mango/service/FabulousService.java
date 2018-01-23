package com.mango.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mango.mapper.FabulousMapper;
import com.mango.model.Fabulous;
import com.mango.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class FabulousService {
    @Autowired
    private FabulousMapper fabulousMapper;
    public int insert(Fabulous fabulous){
        fabulous.setId(UuidUtil.getUuid32());
        fabulous.setCreateTime(new Date());
        int result= fabulousMapper.insert(fabulous);
        return result;
    }
    public int update(Fabulous fabulous){
        fabulous.setUpdateTime(new Date());
        int result= fabulousMapper.updateByPrimaryKey(fabulous);
        return result;
    }
    public int delete(String id){
        int result= fabulousMapper.deleteByPrimaryKey(id);
        return result;
    }
    public Fabulous selectfindByid(String id){
        Fabulous result= fabulousMapper.selectByPrimaryKey(id);
        return result;
    }
    //分页查询用户
    public PageInfo<Fabulous> pageInfo(Integer  pageNo, Integer  pageSize, String uid){
        PageHelper.startPage(pageNo,pageSize);
        List<Fabulous> commentList = fabulousMapper.findAll(uid);
        PageInfo<Fabulous> pagelist = new PageInfo<Fabulous>(commentList);
        return pagelist;
    }
}