package com.mango.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mango.config.PageBean;
import com.mango.model.Picture;
import com.mango.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author baixiufeng
 * @Description:
 * @Date: Created in 10:20 10:20
 */
@RestController
@RequestMapping("/picture")
public class PictureController {
    @Autowired
    private PictureService pictureService;
    @RequestMapping(value="/page",method = RequestMethod.GET)
    public PageInfo<Picture> pages(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "uid",required = false) String uid){
        PageHelper.startPage(pageNo,pageSize);
        List<Picture> picturesList = pictureService.pictureList(uid);
        PageInfo<Picture> pagelist = new PageInfo<Picture>(picturesList);
        return pagelist;
    }

    @RequestMapping(value="/selectByid",method=RequestMethod.GET)
    public Picture selectByid(String id){
        return pictureService.selectfindByid(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String savePicture(@RequestBody Picture picture){
        int result = pictureService.insert(picture);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/del",method=RequestMethod.GET)
    public String delPicture(String id){
        int result= pictureService.delete(id);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String update(@RequestBody Picture picture){
        int result= pictureService.update(picture);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }
}
