package com.mango.service;


import com.mango.mapper.ProposeMapper;
import com.mango.model.Propose;
import com.mango.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;


/**
 * @author baixiufeng
 * @Description:
 * @Date: Created in 15:51 15:51
 */
@Service
public class ProposeService {
    @Autowired
    private ProposeMapper mapper;

    public int insert(Propose propose){
        propose.setId(UuidUtil.getUuid32());
        propose.setCreateTime(new Date());
        int result= mapper.insert(propose);
        return result;
    }
    public int update(Propose propose){
        propose.setUpdateTime(new Date());
        int result= mapper.updateByPrimaryKeySelective(propose);
        return result;
    }
    public int delete(String id){
        int result= mapper.deleteByPrimaryKey(id);
        return result;
    }
    public Propose selectfindByid(String id){
        Propose result= mapper.selectByPrimaryKey(id);
        return result;
    }

}
