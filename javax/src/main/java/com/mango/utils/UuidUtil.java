package com.mango.utils;

import java.util.UUID;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * Created by Administrator on 2017/7/20.
 */
public class UuidUtil {
    private static Log log = LogFactory.getLog(UuidUtil.class);

    public static String getUuid32(){
        String uuid=UUID.randomUUID().toString();
        return uuid.replaceAll("-","");
    }
}
