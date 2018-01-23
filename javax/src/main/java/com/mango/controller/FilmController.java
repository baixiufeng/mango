package com.mango.controller;

import com.github.pagehelper.PageInfo;
import com.mango.model.Film;
import com.mango.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author baixiufeng
 * @Description: 银行卡管理类
 * @Date: Created in 15:09 15:09
 */
@RestController
@RequestMapping("/film")
public class FilmController {
    
    @Autowired
    private FilmService filmService;

    @RequestMapping(value="/page",method = RequestMethod.GET)
    public PageInfo<Film> pages(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "uid",required = false) String uid){
        return filmService.pageInfo(pageNo, pageSize, uid);
    }

    @RequestMapping(value="/selectByid",method=RequestMethod.GET)
    public Film selectByid(String id){
        return filmService.selectfindByid(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveFilm(@RequestBody Film film){
        int result = filmService.insert(film);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/del",method=RequestMethod.GET)
    public String delFilm(String id){
        int result= filmService.delete(id);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String update(@RequestBody Film film) {
        int result = filmService.update(film);
        if (result == 1) {
            return "success";
        } else {
            return "fail";
        }
    }
}
