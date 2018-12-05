package com.tssweb.service;

import com.tssweb.dto.BaseDto;
import com.tssweb.dto.LoginDto;
import com.tssweb.dto.UserSetDto;

import java.util.Map;

public interface IUserService {

    /**
     * 用户注册
     */
    public BaseDto register(Map<String, String> var);

    /**
     * 用户登录
     */
    public LoginDto login(Map<String, String> var);

    /**
     * 获取用户设置
     */
    public UserSetDto getSetInfo(Map<String, String> var);

    /**
     * 修改用户设置
     */
    public BaseDto putSetInfo(Map<String, String> var);
}
