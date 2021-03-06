package com.tssweb.service.impl;

import com.tssweb.dao.IHardwareDao;
import com.tssweb.dao.IUserDao;
import com.tssweb.dto.BaseDto;
import com.tssweb.dto.LoginDto;
import com.tssweb.dto.UserSetDto;
import com.tssweb.entity.HardwareSetEntity;
import com.tssweb.entity.UserEntity;
import com.tssweb.entity.UserSetEntity;
import com.tssweb.service.IUserService;
import com.tssweb.timer.OutTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IHardwareDao iHardwareDao;
    @Autowired
    private IUserDao iUserDao;

    @Autowired
    private LoginDto loginDto;
    @Autowired
    private BaseDto baseDto;
    @Autowired
    private UserSetDto userSetDto;

    @Override
    public BaseDto register(Map<String, String> var) {
        String nickname = var.get("nickname");
        String username = var.get("username");
        String password = var.get("password");

        //通过用户名获取用户信息
        UserEntity userEntity = iUserDao.GetUserByUserName(username);
        if (null != userEntity){
            baseDto.setCode(1);
            baseDto.setMessage("用户已存在");
            return baseDto;
        }

        userEntity = new UserEntity();
        userEntity.setNICKNAME(nickname);
        userEntity.setUSERNAME(username);
        userEntity.setPASSWORD(password);
        if (iUserDao.AddUser(userEntity).equals(0)){
            baseDto.setCode(1);
            baseDto.setMessage("注册失败");
            return baseDto;
        }else{
            baseDto.setCode(0);
            baseDto.setMessage("注册成功");
            return baseDto;
        }
    }

    @Override
    public LoginDto login(Map<String, String> var) {
        String username = var.get("username");
        String password = var.get("password");

        if (username.isEmpty() || password.isEmpty()){
            loginDto.setCode(1);
            loginDto.setMessage("用户名或密码不能为空");
            return loginDto;
        }

        //通过用户名获取用户信息
        UserEntity userEntity = iUserDao.GetUserByUserName(username);
        if (null == userEntity){
            loginDto.setCode(1);
            loginDto.setMessage("该用户不存在");
            return loginDto;
        }

        if (!userEntity.getPASSWORD().equalsIgnoreCase(password)){
            loginDto.setCode(1);
            loginDto.setMessage("用户名与密码不匹配");
            return loginDto;
        }else {
            loginDto.setCode(0);
            loginDto.setMessage("登录成功");
            loginDto.setUid(userEntity.getUID());
            loginDto.setNickname(userEntity.getUSERNAME()); //根据需求将主页显示的名称为登陆的用户名
            return loginDto;
        }
    }

    @Transactional
    @Override
    public UserSetDto getSetInfo(Integer uid) {
        HardwareSetEntity hardwareSetEntity = iHardwareDao.GetHardwareSetByUID(uid);
        if (hardwareSetEntity == null){
            userSetDto.setCode(1);
            userSetDto.setMessage("未查到用户硬件设置信息");
            return userSetDto;
        }
        UserSetEntity userSetEntity = iUserDao.GetUserSetInfoByUID(uid);
        if (null == userSetEntity){
            userSetDto.setCode(1);
            userSetDto.setMessage("未查到用户系统设置信息");
            return userSetDto;
        }

        userSetDto.setCode(0);
        userSetDto.setMessage("成功获取到用户设置信息");
        userSetDto.setSensitivity(hardwareSetEntity.getFRRANGE());
        userSetDto.setTagstatus(String.valueOf(hardwareSetEntity.getFRSTATE()));
        userSetDto.setOnstarttime(userSetEntity.getONSTARTTIME());
        userSetDto.setOnendtime(userSetEntity.getONENDTIME());
        userSetDto.setOffstarttime(userSetEntity.getOFFSTARTTIME());
        userSetDto.setOffendtime(userSetEntity.getOFFENDTIME());
        userSetDto.setTimeout(userSetEntity.getTIMEOUT());
        return userSetDto;
    }

    @Override
    public BaseDto putSetInfo(Integer uid, Map<String, String> var) {

        UserSetEntity userSetEntity = iUserDao.GetUserSetInfoByUID(uid);
        if (null == userSetEntity){
            userSetEntity = new UserSetEntity();
            userSetEntity.setUID(uid);
            userSetEntity.setONSTARTTIME(var.get("onstarttime"));
            userSetEntity.setONENDTIME(var.get("onendtime"));
            userSetEntity.setOFFSTARTTIME(var.get("offstarttime"));
            userSetEntity.setOFFENDTIME(var.get("offendtime"));
            userSetEntity.setTIMEOUT(Integer.valueOf(var.get("timeout")));
            iUserDao.AddUserSet(userSetEntity);
        } else {
            userSetEntity.setONSTARTTIME(var.get("onstarttime"));
            userSetEntity.setONENDTIME(var.get("onendtime"));
            userSetEntity.setOFFSTARTTIME(var.get("offstarttime"));
            userSetEntity.setOFFENDTIME(var.get("offendtime"));
            userSetEntity.setTIMEOUT(Integer.valueOf(var.get("timeout")));
            iUserDao.UpdateUserSetByUID(userSetEntity);
        }
        OutTask.timeout = Integer.valueOf(var.get("timeout"));
        baseDto.setCode(0);
        baseDto.setMessage("修改设置信息成功");
        return baseDto;
    }
}
