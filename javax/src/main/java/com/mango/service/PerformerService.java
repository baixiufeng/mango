package com.mango.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mango.mapper.PerformerMapper;
import com.mango.model.Performer;
import com.mango.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author baixiufeng
 * @Description:
 * @Date: Created in 10:21 10:21
 */
@Service
public class PerformerService {
    @Autowired
    private PerformerMapper performerMapper;
    public int insert(Performer performer){
        performer.setId(UuidUtil.getUuid32());
        performer.setCreateTime(new Date());
        int result= performerMapper.insert(performer);
        return result;
    }
    public int update(Performer performer){
        performer.setUpdateTime(new Date());
        int result= performerMapper.updateByPrimaryKeySelective(performer);
        return result;
    }
    public int delete(String id){
        int result= performerMapper.deleteByPrimaryKey(id);
        return result;
    }
    public Performer selectfindByid(String id){
        Performer result= performerMapper.selectByPrimaryKey(id);
        return result;
    }
    //分页查询用户
    public PageInfo<Performer> pageInfo(Integer  pageNo, Integer  pageSize,String uid){
        PageHelper.startPage(pageNo,pageSize);
        List<Performer> PerformersList = performerMapper.findAll(uid);
        PageInfo<Performer> pagelist = new PageInfo<Performer>(PerformersList);
        return pagelist;
    }
}
