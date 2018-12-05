package com.tssweb.service.impl;

import com.tssweb.dto.BaseDto;
import com.tssweb.dto.LoginDto;
import com.tssweb.dto.UserSetDto;
import com.tssweb.entity.UserEntity;
import com.tssweb.service.IUserService;
import com.tssweb.service.db.IUserDB;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDB iUserDB;

    @Autowired
    private LoginDto loginDto;

    @Override
    public BaseDto register(Map<String, String> var) {
        //
        return null;
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

        //查询用户
        UserEntity userEntity = iUserDB.GetUserByUserName(username);
        if (userEntity == null){
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
