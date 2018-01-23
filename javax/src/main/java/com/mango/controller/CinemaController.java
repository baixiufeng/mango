package com.mango.controller;

import com.github.pagehelper.PageInfo;
import com.mango.model.Cinema;
import com.mango.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author baixiufeng
 * @Description: 银行卡管理类
 * @Date: Created in 15:09 15:09
 */
@RestController
@RequestMapping("/cinema")
public class CinemaController {
    
    @Autowired
    private CinemaService cinemaService;

    @RequestMapping(value="/page",method = RequestMethod.GET)
    public PageInfo<Cinema> pages(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "uid",required = false) String uid){
        return cinemaService.pageInfo(pageNo, pageSize, uid);
    }

    @RequestMapping(value="/selectByid",method=RequestMethod.GET)
    public Cinema selectByid(String id){
        return cinemaService.selectfindByid(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveCinema(@RequestBody Cinema cinema){
        int result = cinemaService.insert(cinema);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/del",method=RequestMethod.GET)
    public String delCinema(String id){
        int result= cinemaService.delete(id);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String update(@RequestBody Cinema cinema) {
        int result = cinemaService.update(cinema);
        if (result == 1) {
            return "success";
        } else {
            return "fail";
        }
    }
}
