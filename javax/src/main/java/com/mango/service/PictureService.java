package com.mango.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mango.config.PageBean;
import com.mango.mapper.PictureMapper;
import com.mango.model.Picture;
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
public class PictureService {
    @Autowired
    private PictureMapper pictureMapper;
    public int insert(Picture picture){
        picture.setId(UuidUtil.getUuid32());
        picture.setCreatTime(new Date());
        int result= pictureMapper.insert(picture);
        return result;
    }
    public int update(Picture picture){
        picture.setUpdateTime(new Date());
        int result= pictureMapper.updateByPrimaryKeySelective(picture);
        return result;
    }
    public int delete(String id){
        int result= pictureMapper.deleteByPrimaryKey(id);
        return result;
    }
    public Picture selectfindByid(String id){
        Picture result= pictureMapper.selectByPrimaryKey(id);
        return result;
    }
    //分页查询用户
    public List<Picture> pictureList(String uid){
        return pictureMapper.findAll(uid);
    }

    public PageBean<Picture> selectPictureList(Integer pageNum, Integer pageSize, String uid){
        PageHelper.startPage(pageNum,pageSize);
        List<Picture> list = pictureMapper.findAll(uid);
        return new PageBean<>(list);
    }
}
