package com.tssweb.service.impl;

import com.tssweb.dao.IUserDao;
import com.tssweb.dto.BaseDto;
import com.tssweb.dto.LoginDto;
import com.tssweb.dto.UserSetDto;
import com.tssweb.entity.UserEntity;
import com.tssweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao iUserDao;

    @Autowired
    private LoginDto loginDto;
    @Autowired
    private BaseDto baseDto;

    @Override
    public BaseDto register(Map<String, String> var) {
        String username = var.get("username");
        String password = var.get("password");

        //通过用户名获取用户信息
        UserEntity userEntity = iUserDao.GetUserByUserName(username);
        if (!userEntity.equals(null)){
            baseDto.setCode(1);
            baseDto.setMessage("用户已存在");
            return baseDto;
        }

        userEntity = new UserEntity();
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
        if (!userEntity.equals(null)){
            loginDto.setCode(1);
            loginDto.setMessage("该用户不存在");
            return loginDto;
        }

        if (!userEntity.getPASSWORD().equals(password)){
            loginDto.setCode(1);
            loginDto.setMessage("用户名与密码不匹配");
            return loginDto;
        }else {
            loginDto.setCode(0);
            loginDto.setMessage("登录成功");
            loginDto.setUid(userEntity.getUID());
            return loginDto;
        }
    }

    @Override
    public UserSetDto getSetInfo(Map<String, String> var) {
        return null;
    }

    @Override
    public BaseDto putSetInfo(Map<String, String> var) {
        return null;
    }
}
