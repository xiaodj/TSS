package com.tssweb.service.impl;

import com.tssweb.dao.IRecordDao;
import com.tssweb.dao.IWorkerDao;
import com.tssweb.dto.*;
import com.tssweb.entity.LicenceEntity;
import com.tssweb.entity.RecordEntity;
import com.tssweb.entity.TagEntity;
import com.tssweb.entity.WorkerEntity;
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

    @Transactional
    @Override
    public RecordsDto getRecords(Integer uid, String sdt, String edt) {

        List<RecordEntity> recordEntityList = iRecordDao.GetRecordsByDate(sdt, edt);
        if (recordEntityList.size() == 0){
            recordsDto.setCode(1);
            recordsDto.setMessage("未获取到相关记录信息");
            return recordsDto;
        }
        return dealRecords(recordEntityList);
    }

    @Transactional
    @Override
    public RecordsDto getRecords(Integer uid, String wid, String sdt, String edt) {
        List<RecordEntity> recordEntityList = iRecordDao.GetRecordsByDateAndWID(sdt, edt, wid);
        if (recordEntityList.size() == 0){
            recordsDto.setCode(1);
            recordsDto.setMessage("未获取到相关记录信息");
            return recordsDto;
        }
        return dealRecords(recordEntityList);
    }

    private RecordsDto dealRecords(List<RecordEntity> recordEntityList){

        List<RecordInfo> recordInfoList = new ArrayList<RecordInfo>();
        List<RecWorkerInfo> recWorkerInfoList = null;
        List<RecTimeInfo> recTimeInfoList = null;
        RecTimeInfo recTimeInfo = null;

        String strDate = "";    //临时保存记录日期
        String strWID="";       //临时保存员工编号

        Map<String, WorkerDto> workerMap = new HashMap<String, WorkerDto>();//临时保存已经获取过的员工信息
        for (Integer index = 0; index < recordEntityList.size(); index++){
            RecordEntity recordEntity = recordEntityList.get(index);
            if (!workerMap.containsKey(recordEntity.getWID())){
                WorkerEntity workerEntity = iWorkerDao.GetWorkerInfo(recordEntity.getWID());
                List<LicenceEntity> licenceEntityList = iWorkerDao.GetLicenceInfoByWID(recordEntity.getWID());
                WorkerDto workerDto = new WorkerDto();
                workerDto.setChname(workerEntity.getWKCHNAME());
                workerDto.setSurname(workerEntity.getWKSURNAME());
                workerDto.setEnname(workerEntity.getWKENNAME());
                List<LicencesInfo> licencesInfoList = new ArrayList<LicencesInfo>();
                for (LicenceEntity licenceEntity: licenceEntityList) {
                    LicencesInfo licencesInfo = new LicencesInfo();
                    licencesInfo.setLcname(licenceEntity.getLCNAME());
                    licencesInfo.setLcdate(licenceEntity.getLCDATE());
                    licencesInfoList.add(licencesInfo);
                }
                workerDto.setLicences(licencesInfoList);
                workerMap.put(recordEntity.getWID(), workerDto);
            }

            //根据日期不同
            if (!recordEntity.getRCDATE().equals(strDate)){
                strDate = recordEntity.getRCDATE();
                RecordInfo recordInfo = new RecordInfo();
                recordInfo.setDate(recordEntity.getRCDATE());
                recWorkerInfoList = new ArrayList<RecWorkerInfo>();
                recordInfo.setWorkers(recWorkerInfoList);
                recordInfoList.add(recordInfo);
            }

            //根据员工编号不同
            if (!recordEntity.getWID().equals(strWID)){
                strWID = recordEntity.getWID();
                RecWorkerInfo recWorkerInfo = new RecWorkerInfo();
                WorkerDto workerDto = workerMap.get(recordEntity.getWID());
                recWorkerInfo.setWid(recordEntity.getWID());
                recWorkerInfo.setChname(workerDto.getChname());
                recWorkerInfo.setSurname(workerDto.getSurname());
                recWorkerInfo.setEnname(workerDto.getEnname());
                recWorkerInfo.setLicences(workerDto.getLicences());
                recTimeInfoList = new ArrayList<RecTimeInfo>();
                recWorkerInfo.setTimes(recTimeInfoList);
                recWorkerInfoList.add(recWorkerInfo);
            }

            if (recordEntity.getTAGSTATE() == 0){
                recTimeInfo = new RecTimeInfo();
                recTimeInfoList.add(recTimeInfo);
                recTimeInfo.setIntime(recordEntity.getRCTIME());
            }else{
                if (index == 0){
                    recTimeInfo = new RecTimeInfo();
                    recTimeInfoList.add(recTimeInfo);
                }
                recTimeInfo.setOuttime(recordEntity.getRCTIME());
            }
        }

        recordsDto.setCode(0);
        recordsDto.setMessage("成功获取记录信息");
        recordsDto.setRecords(recordInfoList);
        return recordsDto;
    }
}
