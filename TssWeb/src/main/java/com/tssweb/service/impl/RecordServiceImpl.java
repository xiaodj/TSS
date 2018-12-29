package com.tssweb.service.impl;

import com.tssweb.dao.IRecordDao;
import com.tssweb.dao.IWorkerDao;
import com.tssweb.dto.*;
import com.tssweb.entity.*;
import com.tssweb.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecordServiceImpl implements IRecordService {

    @Autowired
    private IRecordDao iRecordDao;
    @Autowired
    private IWorkerDao iWorkerDao;

    @Autowired
    private RecordsDto recordsDto;

    private static Map<String, WorkersEntity> workersEntityMap;

    static {
        workersEntityMap = new HashMap<String, WorkersEntity>();
    }

    @Transactional
    @Override
    public RecordsDto getRecords(Integer uid, String wid, String sdt, String edt) {

        if (workersEntityMap.size() <= 0){
            List<WorkersEntity> workersEntityList = iWorkerDao.GetWorkersInfo();
            for (WorkersEntity workersEntity:workersEntityList) {
                workersEntityMap.put(workersEntity.getWID(), workersEntity);
            }
        }

        List<RecordEntity> recordEntityList = new ArrayList<RecordEntity>();
        if (!wid.isEmpty() && sdt.isEmpty() && edt.isEmpty())
            recordEntityList = iRecordDao.GetRecordsByWID(wid);
        if (wid.isEmpty() && !sdt.isEmpty() && !edt.isEmpty())
            recordEntityList = iRecordDao.GetRecordsByDate(sdt, edt);
        if (!wid.isEmpty() && !sdt.isEmpty() && !edt.isEmpty())
            recordEntityList = iRecordDao.GetRecordsByDateAndWID(sdt, edt, wid);;

        if (recordEntityList.size() == 0){
            recordsDto.setCode(1);
            recordsDto.setMessage("未获取到相关记录信息");
            return recordsDto;
        }
        return dealRecords(recordEntityList);
    }

    private RecordsDto dealRecords(List<RecordEntity> recordEntityList){

        List<RecordInfo> recordInfoList = new ArrayList<RecordInfo>();
        RecordInfo recordInfo = null;
        RecordEntity recordEntityTemp = null;
        for (Integer index = 0; index < recordEntityList.size(); index++){
            RecordEntity recordEntity = recordEntityList.get(index);
            if (recordEntity == null){
                recordEntityTemp = recordEntity;
                recordInfo = new RecordInfo();
                recordInfo.setDate(recordEntity.getRCDATE());   //
                continue;
            }



        }

        recordsDto.setCode(0);
        recordsDto.setMessage("成功获取记录信息");
        recordsDto.setRecords(recordInfoList);
        return recordsDto;
    }
}
