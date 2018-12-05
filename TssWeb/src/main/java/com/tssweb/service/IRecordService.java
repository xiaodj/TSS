package com.tssweb.service;

import com.tssweb.dto.BaseDto;
import com.tssweb.dto.RecordsDto;

import java.util.Map;

public interface IRecordService {

    /**
     * 获取记录信息
     */
    public RecordsDto getRecords(Map<String, String> var);
}
