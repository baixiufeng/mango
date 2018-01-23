package com.mango.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mango.mapper.ActivityDetailMapper;
import com.mango.model.ActivityDetail;
import com.mango.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author baixiufeng
 * @Description:
 * @Date: Created in 13:43 13:43
 */
@Service
public class ActivityDetailService {
    @Autowired
    private ActivityDetailMapper activityDetailMapper;

    public int insert(ActivityDetail activityDetail){
        activityDetail.setId(UuidUtil.getUuid32());
        activityDetail.setCreatTime(new Date());
        int result= activityDetailMapper.insert(activityDetail);
        return result;
    }
    public int update(ActivityDetail activityDetail){
        activityDetail.setUpdateTime(new Date());
        int result= activityDetailMapper.updateByPrimaryKeySelective(activityDetail);
        return result;
    }
    public int delete(String id){
        int result= activityDetailMapper.deleteByPrimaryKey(id);
        return result;
    }
    public ActivityDetail selectfindByid(String id){
        ActivityDetail result= activityDetailMapper.selectByPrimaryKey(id);
        return result;
    }
    //分页查询用户
    public PageInfo<ActivityDetail> pageInfo(Integer  pageNo, Integer  pageSize,String uid){
        PageHelper.startPage(pageNo,pageSize);
        List<ActivityDetail> activityDetailList = activityDetailMapper.findAll(uid);
        PageInfo<ActivityDetail> pagelist = new PageInfo<ActivityDetail>(activityDetailList);
        return pagelist;
    }
}
