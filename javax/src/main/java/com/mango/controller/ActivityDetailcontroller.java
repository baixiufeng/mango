package com.mango.controller;

import com.github.pagehelper.PageInfo;
import com.mango.model.ActivityDetail;
import com.mango.service.ActivityDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author baixiufeng
 * @Description:
 * @Date: Created in 14:30 14:30
 */
@RestController
@RequestMapping("/activity")
public class ActivityDetailcontroller {
    @Autowired
    private ActivityDetailService activityDetailService;

    @RequestMapping(value="/page",method = RequestMethod.GET)
    public PageInfo<ActivityDetail> pages(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize,@RequestParam(value = "uid",required = false) String uid){
        return activityDetailService.pageInfo(pageNo, pageSize, uid);
    }

    @RequestMapping(value="/selectByid",method=RequestMethod.GET)
    public ActivityDetail selectByid(String id){
        return activityDetailService.selectfindByid(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveactivityDetail(@RequestBody ActivityDetail activityDetail){
       int result = activityDetailService.insert(activityDetail);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/delactivity",method=RequestMethod.GET)
    public String delactivity(String id){
       int result= activityDetailService.delete(id);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String updateactivity(@RequestBody ActivityDetail activityDetail){
        int result= activityDetailService.update(activityDetail);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }
}
