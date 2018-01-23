package com.mango.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mango.mapper.CinemaMapper;
import com.mango.model.Cinema;
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
public class CinemaService {
    @Autowired
    private CinemaMapper cinemaMapper;

    public int insert(Cinema cinema){
        cinema.setId(UuidUtil.getUuid32());
        cinema.setCreateTime(new Date());
        int result= cinemaMapper.insert(cinema);
        return result;
    }
    public int update(Cinema cinema){
        cinema.setUpdateTime(new Date());
        int result= cinemaMapper.updateByPrimaryKeySelective(cinema);
        return result;
    }
    public int delete(String id){
        int result= cinemaMapper.deleteByPrimaryKey(id);
        return result;
    }
    public Cinema selectfindByid(String id){
        Cinema result= cinemaMapper.selectByPrimaryKey(id);
        return result;
    }
    //分页查询用户
    public PageInfo<Cinema> pageInfo(Integer  pageNo, Integer  pageSize, String filmName){
        PageHelper.startPage(pageNo,pageSize);
        List<Cinema> cinemaList = cinemaMapper.findAll(filmName);
        PageInfo<Cinema> pagelist = new PageInfo<Cinema>(cinemaList);
        return pagelist;
    }
}
