package com.mango.controller;

import com.github.pagehelper.PageInfo;
import com.mango.model.Contacts;
import com.mango.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author baixiufeng
 * @Description: 银行卡管理类
 * @Date: Created in 15:09 15:09
 */
@RestController
@RequestMapping("/fontacts")
public class ContactsController {
    
    @Autowired
    private ContactsService contactsService;

    @RequestMapping(value="/selectByid",method=RequestMethod.GET)
    public Contacts selectByid(String id){
        return contactsService.selectfindByid(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveContacts(@RequestBody Contacts contacts){
        int result = contactsService.insert(contacts);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/del",method=RequestMethod.GET)
    public String delContacts(String id){
        int result= contactsService.delete(id);
        if(result ==1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String update(@RequestBody Contacts contacts) {
        int result = contactsService.update(contacts);
        if (result == 1) {
            return "success";
        } else {
            return "fail";
        }
    }
}
