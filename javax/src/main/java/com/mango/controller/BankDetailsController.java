package com.mango.controller;

import com.github.pagehelper.PageInfo;
import com.mango.model.BankDetails;
import com.mango.service.BankDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author baixiufeng
 * @Description: 银行卡管理类
 * @Date: Created in 15:09 15:09
 */
@RestController
@RequestMapping("/bank")
public class BankDetailsController {
    
    @Autowired
    private BankDetailsService bankDetailsService;

    @RequestMapping(value="/page",method = RequestMethod.GET)
    public PageInfo<BankDetails> pages(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "uid",required = false) String uid){
        return bankDetailsService.pageInfo(pageNo, pageSize, uid);
    }

    @RequestMapping(value="/selectByid",method=RequestMethod.GET)
    public BankDetails selectByid(String id){
        return bankDetailsService.selectfindByid(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String savePicture(@RequestBody BankDetails bankDetails){
        int result = bankDetailsService.insert(bankDetails);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/del",method=RequestMethod.GET)
    public String delbankDetails(String id){
        int result= bankDetailsService.delete(id);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String update(@RequestBody BankDetails bankDetails) {
        int result = bankDetailsService.update(bankDetails);
        if (result == 1) {
            return "success";
        } else {
            return "fail";
        }
    }
}
