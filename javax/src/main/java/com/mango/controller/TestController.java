package com.mango.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/upuser",method = RequestMethod.GET)
    public String updateuser(){
        return "updateuser";
    }

    @RequestMapping(value = "/picturepage",method = RequestMethod.GET)
    public String picturepage(){
        return "picturelist";
    }

    @RequestMapping(value = "/picture_detail",method = RequestMethod.GET)
    public String picture_detail(){
        return "picture_detail";
    }

    @RequestMapping(value = "/activitylist",method = RequestMethod.GET)
    public String activitylist(){
        return "activity_list";
    }

    @RequestMapping(value = "/activitydetail",method = RequestMethod.GET)
    public String activitydetail(){
        return "activity_detail";
    }
}
