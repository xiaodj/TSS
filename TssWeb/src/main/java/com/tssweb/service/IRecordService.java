package com.tssweb.service;

import com.tssweb.dto.BaseDto;
import com.tssweb.dto.RecordsDto;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Map;

public interface IRecordService {

    /**
     * 获取记录信息
     */
    public RecordsDto getRecords(Integer uid, String sdt, String edt);

    /**
     * 获取记录信息
     */
    public RecordsDto getRecords(Integer uid, String wid, String sdt, String edt);
}
