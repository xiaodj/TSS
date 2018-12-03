package com.tssweb.service;

import com.tssweb.dto.BaseDto;
import com.tssweb.dto.LoginDto;

import java.util.Map;

public interface IUserService {

    /**
     * 用户登录
     */
    public LoginDto login(Map<String, String> var);

    /**
     * 用户设置
     */
    public BaseDto set(Map<String, String> var);
}
