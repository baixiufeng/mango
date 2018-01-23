package com.mango.controller;

import com.github.pagehelper.PageInfo;
import com.mango.model.Fabulous;
import com.mango.service.FabulousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author baixiufeng
 * @Description: 银行卡管理类
 * @Date: Created in 15:09 15:09
 */
@RestController
@RequestMapping("/fabulous")
public class FabulousController {
    
    @Autowired
    private FabulousService fabulousService;

    @RequestMapping(value="/page",method = RequestMethod.GET)
    public PageInfo<Fabulous> pages(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "uid",required = false) String uid){
        return fabulousService.pageInfo(pageNo, pageSize, uid);
    }

    @RequestMapping(value="/selectByid",method=RequestMethod.GET)
    public Fabulous selectByid(String id){
        return fabulousService.selectfindByid(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveFabulous(@RequestBody Fabulous fabulous){
        int result = fabulousService.insert(fabulous);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/del",method=RequestMethod.GET)
    public String delFabulous(String id){
        int result= fabulousService.delete(id);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String update(@RequestBody Fabulous fabulous) {
        int result = fabulousService.update(fabulous);
        if (result == 1) {
            return "success";
        } else {
            return "fail";
        }
    }
}
