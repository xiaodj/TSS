package com.tssweb.service;

import com.tssweb.dto.BaseDto;

import java.util.Map;

public interface IHardwareService {
    /**
     * 硬件设置
     */
    public BaseDto set(Map<String, String> var);
}
