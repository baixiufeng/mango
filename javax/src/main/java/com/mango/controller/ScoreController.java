package com.mango.controller;

import com.github.pagehelper.PageInfo;
import com.mango.model.Score;
import com.mango.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author baixiufeng
 * @Description:
 * @Date: Created in 10:20 10:20
 */
@RestController
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;
    @RequestMapping(value="/page",method = RequestMethod.GET)
    public PageInfo<Score> pages(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "uid",required = false) String uid){
        return scoreService.pageInfo(pageNo, pageSize, uid);
    }

    @RequestMapping(value="/selectByid",method=RequestMethod.GET)
    public Score selectByid(String id){
        return scoreService.selectfindByid(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveScore(@RequestBody Score score){
        int result = scoreService.insert(score);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/del",method=RequestMethod.GET)
    public String delScore(String id){
        int result= scoreService.delete(id);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String update(@RequestBody Score score){
        int result= scoreService.update(score);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }
}
