package com.mango.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mango.mapper.UserInfoMapper;
import com.mango.model.UserInfo;
import com.mango.utils.QiniuUtil;
import com.mango.utils.UuidUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author baixiufeng
 * @Description: 用户Service
 * @Date: Created in 12:28 12:28
 */
@Service
public class UserInfoService {
    @Autowired
    public UserInfoMapper userInfoMapper;
    @Autowired
    public QiniuUtil qiniuUtil;
    //保存用户
    public Integer save(UserInfo userInfo, MultipartFile multipartFile) throws IOException {
        userInfo.setId(UuidUtil.getUuid32());
        userInfo.setCreatTime(new Date());
        FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
        String imgpath = qiniuUtil.uploadImg(inputStream, userInfo.getName()+userInfo.getId());
        if (!imgpath.isEmpty()){
            userInfo.setHeadPortrait(imgpath);
        }
        Integer result=userInfoMapper.insert(userInfo);
        return result;
    }
    //更新用户信息
    public Integer update(UserInfo userInfo, MultipartFile multipartFile){
        userInfo.setUpdateTime(new Date());
        UserInfo userInfo1 =userInfoMapper.selectByPrimaryKey(userInfo.getId());
        if(!userInfo1.getHeadPortrait().isEmpty()){
            String headPortrait = userInfo.getHeadPortrait();
            try {
                Response result=qiniuUtil.delImgFile(StringUtils.substringAfter(userInfo1.getHeadPortrait(),"/"));
                if (result.statusCode==200){
                    FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
                    String imgpath = qiniuUtil.uploadImg(inputStream, userInfo.getName()+userInfo.getId());
                    if (!imgpath.isEmpty()){
                        userInfo.setHeadPortrait(imgpath);
                    }
                }
            } catch (QiniuException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        Integer result=userInfoMapper.updateByPrimaryKeySelective(userInfo);
        return result;
    }
    //删除一条用户
    public Integer delete(String id){
        Integer result=userInfoMapper.deleteByPrimaryKey(id);
        return result;
    }
    //查询单条用户信息
    public UserInfo selectById(String id){
        UserInfo result=userInfoMapper.selectByPrimaryKey(id);
        return result;
    }

    //分页查询用户
    public PageInfo<UserInfo> pageInfo(Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<UserInfo> userInfoList = userInfoMapper.findAll();
        System.out.print(userInfoList);
        PageInfo<UserInfo> pagelist = new PageInfo<UserInfo>(userInfoList);
        return pagelist;
    }

    public UserInfo findByName(String userName) {
        String email = null;
        String phone = null;
        if (checkEmail(userName)) {
            email = userName;
        }
        if (checkMobileNumber(userName)) {
            phone = userName;
        }
        UserInfo result=userInfoMapper.findByPhoneOrEmail(email,phone);
        return result;
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */

    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证手机号码，11位数字，1开通，第二位数必须是3456789这些数字之一 *
     * @param mobileNumber
     * @return
     */
    public static boolean checkMobileNumber(String mobileNumber) {
        boolean flag = false;
        try {
            // Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Pattern regex = Pattern.compile("^1[345789]\\d{9}$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;

        }
        return flag;
    }
}
