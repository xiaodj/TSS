package com.tssweb.service;

import com.tssweb.dto.BaseDto;
import com.tssweb.dto.HardwareSetDto;

import java.util.Map;

public interface IHardwareService {
    /**
     * 获取硬件设置信息
     */
    public HardwareSetDto getHardwareInfo(Integer uid);

    /**
     * 修改硬件设置信息
     */
    public BaseDto putHardwareInfo(Integer uid, Map<String, String> var);

    /**
     * 修改灵敏度设置
     */
    public BaseDto putDeviceSensitivity(Integer uid, Map<String, String> var);

    /**
     * 修改标签识别状态
     */
    public BaseDto putDeviceTagIdentify(Integer uid, Map<String, String> var);
}
