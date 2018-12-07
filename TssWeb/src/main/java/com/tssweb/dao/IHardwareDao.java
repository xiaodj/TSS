package com.tssweb.dao;

import com.tssweb.entity.HardwareSetEntity;

public interface IHardwareDao {

    /**
     * 通过用户编码获取硬件设置信息
     */
    public HardwareSetEntity GetHardwareSetByUID(Integer uid);

    /**
     * 新增硬件设置信息
     */
    public Integer AddHardwareSet(HardwareSetEntity hardwareSetEntity);

    /**
     * 根据用户编码修改硬件设置信息
     */
    public Integer UpdateHardwareSetByUID(HardwareSetEntity hardwareSetEntity);
}
