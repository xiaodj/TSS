package com.tssweb.service.db;

import com.tssweb.entity.UserEntity;

public interface IUserDB {

    /**
     * 根据用户名获取用户信息
     */
    public UserEntity GetUserByUserName(String username);
}
