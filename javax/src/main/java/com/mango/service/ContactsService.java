package com.mango.service;

import com.mango.model.Contacts;
import com.mango.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;



@Service
public class ContactsService {
    @Autowired
    private ContactsService contactsService;
    public int insert(Contacts contacts){
        contacts.setId(UuidUtil.getUuid32());
        contacts.setCreateTime(new Date());
        int result= contactsService.insert(contacts);
        return result;
    }
    public int update(Contacts contacts){
        contacts.setUpdateTime(new Date());
        int result= contactsService.update(contacts);
        return result;
    }
    public int delete(String id){
        int result= contactsService.delete(id);
        return result;
    }
    public Contacts selectfindByid(String id){
        Contacts result= contactsService.selectfindByid(id);
        return result;
    }

}