package com.tssweb.service.db;

import com.tssweb.entity.UserEntity;
import com.tssweb.entity.UserSetEntity;

public interface IUserDB {

    /**
     * 判断用户名是否存在
     */
    public boolean IsExistUserByUserName(String username);

    /**
     * 新增用户信息
     */
    public boolean AddUser(UserEntity userEntity);

    /**
     * 修改用户信息
     */
    public boolean UpdateUserByUID(Integer uid);

    /**
     * 根据用户名获取用户信息
     */
    public UserEntity GetUserByUserName(String username);

    /**
     * 通过用户编码判断用户设置信息是否存在
     */
    public boolean IsExistUserSetByUID(Integer uid);

    /**
     * 通过用户编码获取用户设置信息
     */
    public UserSetEntity GetUserSetInfoByUID(Integer uid);

    /**
     * 新增用户设置信息
     */
    public boolean AddUserSet(UserSetEntity userSetEntity);

    /**
     * 通过用户编码修改用户设置信息
     */
    public boolean UpdateUserSetByUID(Integer uid);
}
