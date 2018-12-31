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
            if (recordEntityTemp == null){
                WorkersEntity workersEntity = workersEntityMap.get(recordEntity.getWID());
                if (workersEntity == null)
                    continue;
                recordEntityTemp = recordEntity;
                recordInfo = new RecordInfo();
                recordInfo.setDate(recordEntity.getRCDATE());   //
                recordInfo.setWid(recordEntity.getWID());
                if (recordEntity.getTAGSTATE() == 0)
                    recordInfo.setIntime(recordEntity.getRCTIME());
                else
                    recordInfo.setOuttime(recordEntity.getRCTIME());

                recordInfo.setChname(workersEntity.getWKCHNAME());
                recordInfo.setSurname(workersEntity.getWKSURNAME());
                recordInfo.setEnname(workersEntity.getWKENNAME());
                for (LicenceEntity licenceEntity:workersEntity.getLicenceEntities()) {
                    if (licenceEntity.getLCNAME().equals("绿卡")){
                        recordInfo.setLc1(licenceEntity.getLCDATE());
                    }else if (licenceEntity.getLCNAME().equals("密卡")){
                        recordInfo.setLc2(licenceEntity.getLCDATE());
                    }else if (licenceEntity.getLCNAME().equals("CP")){
                        recordInfo.setLc3(licenceEntity.getLCDATE());
                    }
                }
                recordInfoList.add(recordInfo);
                continue;
            }

            //不同日期 或者 不同员工 或者  状态为离开  或者 状态相同
            if (!recordEntity.getRCDATE().equals(recordEntityTemp.getRCDATE())
                    || !recordEntity.getWID().equals(recordEntityTemp.getWID())
                    || recordEntityTemp.getTAGSTATE().equals(1)
                    || recordEntity.getTAGSTATE().equals(recordEntityTemp.getTAGSTATE())){
                WorkersEntity workersEntity = workersEntityMap.get(recordEntity.getWID());
                if (workersEntity == null)
                    continue;
                recordEntityTemp = recordEntity;
                recordInfo = new RecordInfo();
                recordInfo.setDate(recordEntity.getRCDATE());   //
                recordInfo.setWid(recordEntity.getWID());
                if (recordEntity.getTAGSTATE() == 0)
                    recordInfo.setIntime(recordEntity.getRCTIME());
                else
                    recordInfo.setOuttime(recordEntity.getRCTIME());

                recordInfo.setChname(workersEntity.getWKCHNAME());
                recordInfo.setSurname(workersEntity.getWKSURNAME());
                recordInfo.setEnname(workersEntity.getWKENNAME());
                for (LicenceEntity licenceEntity:workersEntity.getLicenceEntities()) {
                    if (licenceEntity.getLCNAME().equals("绿卡")){
                        recordInfo.setLc1(licenceEntity.getLCDATE());
                    }else if (licenceEntity.getLCNAME().equals("密卡")){
                        recordInfo.setLc2(licenceEntity.getLCDATE());
                    }else if (licenceEntity.getLCNAME().equals("CP")){
                        recordInfo.setLc3(licenceEntity.getLCDATE());
                    }
                }
                recordInfoList.add(recordInfo);
            }else{
                recordInfo.setOuttime(recordEntity.getRCTIME());
                recordEntityTemp = recordEntity;
            }
        }

        if (recordInfoList.size() == 0){
            recordsDto.setCode(1);
            recordsDto.setMessage("未获取到相关记录信息");
        }else {
            recordsDto.setCode(0);
            recordsDto.setMessage("成功获取记录信息");
            recordsDto.setRecords(recordInfoList);
        }

        return recordsDto;
    }
}
