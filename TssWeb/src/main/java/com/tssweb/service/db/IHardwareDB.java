package com.tssweb.service.db;

import com.tssweb.entity.HardwareSetEntity;

public interface IHardwareDB {

    /**
     * 通过用户编码判断硬件设置信息是否存在
     */
    public boolean IsExistHardwareByUID(Integer uid);

    /**
     * 通过用户编码获取硬件设置信息
     */
    public HardwareSetEntity GetHardwareSetBy(Integer uid);

    /**
     * 新增硬件设置信息
     */
    public boolean AddHardwareSet(HardwareSetEntity hardwareSetEntity);

    /**
     * 根据用户编码修改硬件设置信息
     */
    public boolean UpdateHardwareSetByUID(Integer uid);
}
