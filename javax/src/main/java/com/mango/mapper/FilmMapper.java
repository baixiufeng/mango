package com.mango.mapper;

import com.mango.model.Film;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FilmMapper {
    int deleteByPrimaryKey(String id);

    int insert(Film record);

    int insertSelective(Film record);

    Film selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Film record);

    int updateByPrimaryKey(Film record);

    List<Film> findAll(@Param("uid") String uid);
}