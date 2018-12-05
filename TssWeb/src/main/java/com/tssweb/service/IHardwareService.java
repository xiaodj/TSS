package com.tssweb.service;

import com.tssweb.dto.BaseDto;
import com.tssweb.dto.HardwareSetDto;

import java.util.Map;

public interface IHardwareService {
    /**
     * 获取硬件设置信息
     */
    public HardwareSetDto getHardwareInfo(Map<String, String> var);

    /**
     * 修改硬件设置信息
     */
    public BaseDto putHardwareInfo(Map<String, String> var);
}
