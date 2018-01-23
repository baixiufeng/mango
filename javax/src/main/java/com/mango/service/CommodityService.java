package com.mango.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mango.mapper.CommodityMapper;
import com.mango.model.Commodity;
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
public class CommodityService {
    @Autowired
    private CommodityMapper commodityMapper;
    public int insert(Commodity comment){
        comment.setId(UuidUtil.getUuid32());
        comment.setCreateTime(new Date());
        int result= commodityMapper.insert(comment);
        return result;
    }
    public int update(Commodity comment){
        comment.setUpdateTime(new Date());
        int result= commodityMapper.updateByPrimaryKeySelective(comment);
        return result;
    }
    public int delete(String id){
        int result= commodityMapper.deleteByPrimaryKey(id);
        return result;
    }
    public Commodity selectfindByid(String id){
        Commodity result= commodityMapper.selectByPrimaryKey(id);
        return result;
    }
    //分页查询用户
    public PageInfo<Commodity> pageInfo(Integer  pageNo, Integer  pageSize, String uid){
        PageHelper.startPage(pageNo,pageSize);
        List<Commodity> commentList = commodityMapper.findAll(uid);
        PageInfo<Commodity> pagelist = new PageInfo<Commodity>(commentList);
        return pagelist;
    }
}
