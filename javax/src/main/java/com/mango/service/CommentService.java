package com.mango.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mango.mapper.CommentMapper;
import com.mango.model.Comment;
import com.mango.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;


@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    public int insert(Comment comment){
        comment.setId(UuidUtil.getUuid32());
        comment.setCreateTime(new Date());
        int result= commentMapper.insert(comment);
        return result;
    }
    public int update(Comment comment){
        comment.setUpdateTime(new Date());
        int result= commentMapper.updateByPrimaryKeySelective(comment);
        return result;
    }
    public int delete(String id){
        int result= commentMapper.deleteByPrimaryKey(id);
        return result;
    }
    public Comment selectfindByid(String id){
        Comment result= commentMapper.selectByPrimaryKey(id);
        return result;
    }
    //分页查询用户
    public PageInfo<Comment> pageInfo(Integer  pageNo, Integer  pageSize, String filmName){
        PageHelper.startPage(pageNo,pageSize);
        List<Comment> commentList = commentMapper.findAll(filmName);
        PageInfo<Comment> pagelist = new PageInfo<Comment>(commentList);
        return pagelist;
    }
}