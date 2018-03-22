package com.mango.controller;

import com.github.pagehelper.PageInfo;
import com.mango.model.Cinema;
import com.mango.model.UserInfo;
import com.mango.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @author baixiufeng
 * @Description:
 * @Date: Created in 16:19 16:19
 */
@RestController
@RequestMapping(value = "/user")
public class UserInfoController {
    private static Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String register(@RequestBody UserInfo userInfo,@RequestParam("file")MultipartFile multipartFile) throws IOException {
       int result= userInfoService.save(userInfo,multipartFile);
       if(result ==1){
           return "success";
       }else {
           return "fail";
       }
    }
    @RequestMapping(value="/selectByid",method=RequestMethod.GET)
    public UserInfo selectByid(String id){
        return userInfoService.selectById(id);
    }


    @RequestMapping(value = "/alluser", method = RequestMethod.GET)
    public PageInfo<UserInfo> UserInfoall(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        PageInfo<UserInfo> userInfoPageInfo= userInfoService.pageInfo(pageNo,pageSize);
        return userInfoPageInfo;
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String delete(@PathVariable String id) throws Exception{
        int result = userInfoService.delete(id);
        logger.info("删除->ID="+id);
        if (result ==1) {
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(@RequestBody UserInfo userInfo,@RequestParam("file")MultipartFile multipartFile){
       int result = userInfoService.update(userInfo,multipartFile);
       logger.info("修改->ID"+userInfo.getId());
        if (result ==1) {
            return "success";
        }else {
            return "fail";
        }
    }


}
