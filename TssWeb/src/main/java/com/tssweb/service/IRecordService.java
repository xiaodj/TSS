package com.tssweb.service;

import com.tssweb.dto.BaseDto;

import java.util.Map;

public interface IRecordService {

    /**
     * 获取记录信息
     */
    public BaseDto getRecords(Map<String, String> var);
}
