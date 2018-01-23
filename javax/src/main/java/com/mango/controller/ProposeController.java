package com.mango.controller;

import com.github.pagehelper.PageInfo;
import com.mango.model.Propose;
import com.mango.service.ProposeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author baixiufeng
 * @Description:
 * @Date: Created in 10:20 10:20
 */
@RestController
@RequestMapping("/propose")
public class ProposeController {
    @Autowired
    private ProposeService proposeService;


    @RequestMapping(value="/selectByid",method=RequestMethod.GET)
    public Propose selectByid(String id){
        return proposeService.selectfindByid(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String savePropose(@RequestBody Propose propose){
        int result = proposeService.insert(propose);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/del",method=RequestMethod.GET)
    public String delPropose(String id){
        int result= proposeService.delete(id);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String update(@RequestBody Propose propose){
        int result= proposeService.update(propose);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }
}
