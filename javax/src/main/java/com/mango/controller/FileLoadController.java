package com.mango.controller;

import com.mango.model.UserInfo;
import com.mango.service.UserInfoService;
import com.mango.utils.QiniuUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.model.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/fileload")
public class FileLoadController {
    @Autowired
    private QiniuUtil qiniuUtil;
    @Autowired
    private UserInfoService userInfoService;



    @RequestMapping("/article/img/qiniu")
    public String uploadImgQiniu(@RequestParam("name") String name, @RequestParam("password") String passwprd,
                                 @RequestParam("file")MultipartFile multipartFile, Model model) throws IOException {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setPassword(passwprd);

        FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
        String path = qiniuUtil.uploadImg(inputStream, userInfo.getName()+multipartFile.getOriginalFilename());
        System.out.print(path);
        return path;
    }

    @RequestMapping("/delimg")
    public String delImgFile(String key) throws QiniuException {
       Response result= qiniuUtil.delImgFile(key);
        return "status:"+result.statusCode;
    }

    @RequestMapping("/getImgFilelist")
    public String getImgFilelist(ModelMap modelMap) throws QiniuException {
        List<FileInfo> result= qiniuUtil.fileInfoList();
        modelMap.put("fileinfo",result);

        return "status:";
    }
    @RequestMapping("/getImgFile")
    public FileInfo getimg(String key){
       return qiniuUtil.getImgFile(key);
    }
}
