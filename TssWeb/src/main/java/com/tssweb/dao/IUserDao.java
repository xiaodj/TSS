package com.tssweb.dao;

import com.tssweb.entity.UserEntity;
import com.tssweb.entity.UserSetEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao {

    /**
     * 新增用户信息
     */
    public Integer AddUser(UserEntity userEntity);

    /**
     * 修改用户信息
     */
    public Integer UpdateUserByUID(UserEntity userEntity);

    /**
     * 根据用户名获取用户信息
     */
    public UserEntity GetUserByUserName(String username);

    /**
     * 通过用户编码获取用户设置信息
     */
    public UserSetEntity GetUserSetInfoByUID(Integer uid);

    /**
     * 新增用户设置信息
     */
    public Integer AddUserSet(UserSetEntity userSetEntity);

    /**
     * 通过用户编码修改用户设置信息
     */
    public Integer UpdateUserSetByUID(UserSetEntity userSetEntity);
}
