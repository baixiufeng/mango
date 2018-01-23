package com.mango.controller;

import com.github.pagehelper.PageInfo;
import com.mango.model.UserDetail;
import com.mango.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author baixiufeng
 * @Description:
 * @Date: Created in 10:20 10:20
 */
@RestController
@RequestMapping("/userDetail")
public class UserDetailController {
    @Autowired
    private UserDetailService userDetailService;
    @RequestMapping(value="/page",method = RequestMethod.GET)
    public PageInfo<UserDetail> pages(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "uid",required = false) String uid){
        return userDetailService.pageInfo(pageNo, pageSize, uid);
    }

    @RequestMapping(value="/selectByid",method=RequestMethod.GET)
    public UserDetail selectByid(String id){
        return userDetailService.selectfindByid(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveUserDetail(@RequestBody UserDetail userDetail){
        int result = userDetailService.insert(userDetail);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/del",method=RequestMethod.GET)
    public String delUserDetail(String id){
        int result= userDetailService.delete(id);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String update(@RequestBody UserDetail userDetail){
        int result= userDetailService.update(userDetail);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }
}
