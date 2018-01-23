package com.mango.controller;

import com.github.pagehelper.PageInfo;
import com.mango.model.MerchantAccess;
import com.mango.service.MerchantAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author baixiufeng
 * @Description: 银行卡管理类
 * @Date: Created in 15:09 15:09
 */
@RestController
@RequestMapping("/merchantAccess")
public class MerchantAccessController {
    
    @Autowired
    private MerchantAccessService merchantAccessService;

    @RequestMapping(value="/page",method = RequestMethod.GET)
    public PageInfo<MerchantAccess> pages(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "uid",required = false) String uid){
        return merchantAccessService.pageInfo(pageNo, pageSize, uid);
    }

    @RequestMapping(value="/selectByid",method=RequestMethod.GET)
    public MerchantAccess selectByid(String id){
        return merchantAccessService.selectfindByid(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveMerchantAccess(@RequestBody MerchantAccess merchantAccess){
        int result = merchantAccessService.insert(merchantAccess);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/del",method=RequestMethod.GET)
    public String delMerchantAccess(String id){
        int result= merchantAccessService.delete(id);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String update(@RequestBody MerchantAccess merchantAccess) {
        int result = merchantAccessService.update(merchantAccess);
        if (result == 1) {
            return "success";
        } else {
            return "fail";
        }
    }
}
