package com.mango.controller;

import com.github.pagehelper.PageInfo;
import com.mango.model.Commodity;
import com.mango.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author baixiufeng
 * @Description: 银行卡管理类
 * @Date: Created in 15:09 15:09
 */
@RestController
@RequestMapping("/fommodity")
public class CommodityController {
    
    @Autowired
    private CommodityService commodityService;

    @RequestMapping(value="/page",method = RequestMethod.GET)
    public PageInfo<Commodity> pages(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "uid",required = false) String uid){
        return commodityService.pageInfo(pageNo, pageSize, uid);
    }

    @RequestMapping(value="/selectByid",method=RequestMethod.GET)
    public Commodity selectByid(String id){
        return commodityService.selectfindByid(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveCommodity(@RequestBody Commodity commodity){
        int result = commodityService.insert(commodity);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/del",method=RequestMethod.GET)
    public String delCommodity(String id){
        int result= commodityService.delete(id);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String update(@RequestBody Commodity commodity) {
        int result = commodityService.update(commodity);
        if (result == 1) {
            return "success";
        } else {
            return "fail";
        }
    }
}
