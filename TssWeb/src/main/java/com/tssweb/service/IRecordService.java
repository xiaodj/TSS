package com.tssweb.service;

import com.tssweb.dto.BaseDto;
import com.tssweb.dto.RecordsDto;
import com.tssweb.entity.RecordEntity;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Map;

public interface IRecordService {

    /**
     * 获取记录信息
     */
    public RecordsDto getRecords(Integer uid, String wid, String sdt, String edt);
}
