package com.mango.controller;

import com.github.pagehelper.PageInfo;
import com.mango.model.Comment;
import com.mango.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author baixiufeng
 * @Description: 银行卡管理类
 * @Date: Created in 15:09 15:09
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    
    @Autowired
    private CommentService commentService;

    @RequestMapping(value="/page",method = RequestMethod.GET)
    public PageInfo<Comment> pages(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "uid",required = false) String uid){
        return commentService.pageInfo(pageNo, pageSize, uid);
    }

    @RequestMapping(value="/selectByid",method=RequestMethod.GET)
    public Comment selectByid(String id){
        return commentService.selectfindByid(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveComment(@RequestBody Comment comment){
        int result = commentService.insert(comment);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/del",method=RequestMethod.GET)
    public String delComment(String id){
        int result= commentService.delete(id);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String update(@RequestBody Comment comment) {
        int result = commentService.update(comment);
        if (result == 1) {
            return "success";
        } else {
            return "fail";
        }
    }
}
