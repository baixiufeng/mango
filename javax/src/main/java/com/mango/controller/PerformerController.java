package com.mango.controller;

import com.github.pagehelper.PageInfo;
import com.mango.model.Performer;
import com.mango.service.PerformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author baixiufeng
 * @Description: 银行卡管理类
 * @Date: Created in 15:09 15:09
 */
@RestController
@RequestMapping("/performer")
public class PerformerController {
    
    @Autowired
    private PerformerService performerService;

    @RequestMapping(value="/page",method = RequestMethod.GET)
    public PageInfo<Performer> pages(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "uid",required = false) String uid){
        return performerService.pageInfo(pageNo, pageSize, uid);
    }

    @RequestMapping(value="/selectByid",method=RequestMethod.GET)
    public Performer selectByid(String id){
        return performerService.selectfindByid(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String savePerformer(@RequestBody Performer performer){
        int result = performerService.insert(performer);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/del",method=RequestMethod.GET)
    public String delPerformer(String id){
        int result= performerService.delete(id);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String update(@RequestBody Performer performer) {
        int result = performerService.update(performer);
        if (result == 1) {
            return "success";
        } else {
            return "fail";
        }
    }
}
