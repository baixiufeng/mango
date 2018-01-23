package com.mango.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mango.mapper.FabulousMapper;
import com.mango.mapper.FilmMapper;
import com.mango.model.Fabulous;
import com.mango.model.Film;
import com.mango.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class FilmService {
    @Autowired
    private FilmMapper mapper;
    public int insert(Film film){
        film.setId(UuidUtil.getUuid32());
        film.setCreateTime(new Date());
        int result= mapper.insert(film);
        return result;
    }
    public int update(Film film){
        film.setUpdateTime(new Date());
        int result= mapper.updateByPrimaryKey(film);
        return result;
    }
    public int delete(String id){
        int result= mapper.deleteByPrimaryKey(id);
        return result;
    }
    public Film selectfindByid(String id){
        Film result= mapper.selectByPrimaryKey(id);
        return result;
    }
    //分页查询用户
    public PageInfo<Film> pageInfo(Integer  pageNo, Integer  pageSize, String uid){
        PageHelper.startPage(pageNo,pageSize);
        List<Film> filmList = mapper.findAll(uid);
        PageInfo<Film> pagelist = new PageInfo<Film>(filmList);
        return pagelist;
    }
}