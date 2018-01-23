package com.mango.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mango.mapper.ScoreMapper;
import com.mango.model.Score;
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
public class ScoreService {
    @Autowired
    private ScoreMapper mapper;

    public int insert(Score score){
        score.setId(UuidUtil.getUuid32());
        score.setCreateTime(new Date());
        int result= mapper.insert(score);
        return result;
    }
    public int update(Score score){
        score.setUpdateTime(new Date());
        int result= mapper.updateByPrimaryKeySelective(score);
        return result;
    }
    public int delete(String id){
        int result= mapper.deleteByPrimaryKey(id);
        return result;
    }
    public Score selectfindByid(String id){
        Score result= mapper.selectByPrimaryKey(id);
        return result;
    }
    //分页查询用户
    public PageInfo<Score> pageInfo(Integer  pageNo, Integer  pageSize, String filmName){
        PageHelper.startPage(pageNo,pageSize);
        List<Score> scoreList = mapper.findAll(filmName);
        PageInfo<Score> pagelist = new PageInfo<Score>(scoreList);
        return pagelist;
    }
}
