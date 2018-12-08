package com.tssweb.service.impl;

import com.tssweb.dao.IWorkerDao;
import com.tssweb.dto.*;
import com.tssweb.entity.LicenceEntity;
import com.tssweb.entity.TagEntity;
import com.tssweb.entity.WorkerEntity;
import com.tssweb.service.IWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WorkerServiceImpl implements IWorkerService {

    @Autowired
    private IWorkerDao iWorkerDao;

    @Autowired
    private BaseDto baseDto;
    @Autowired
    private WorkerDto workerDto;
    @Autowired
    private WorkersDto workersDto;

    @Transactional
    @Override
    public BaseDto addWorker(Integer uid, WorkerDto workerDto) {
        WorkerEntity workerEntity = new WorkerEntity();
        List<LicenceEntity> licenceEntityList = new ArrayList<LicenceEntity>();
        List<TagEntity> tagEntityList = new ArrayList<TagEntity>();

        workerEntity.setUID(uid);
        workerEntity.setWID(workerDto.getWid());
        workerEntity.setWKCHNAME(workerDto.getChname());
        workerEntity.setWKSURNAME(workerDto.getSurname());
        workerEntity.setWKENNAME(workerDto.getEnname());
        workerEntity.setWKCARD(workerDto.getWkcard());
        workerEntity.setWKCCRSID(workerDto.getCcrsid());
        workerEntity.setWKOTHER(workerDto.getOther());
        if (null == workerDto.getImagepath() || workerDto.getImagepath().isEmpty())
            workerEntity.setWKIMAGEPATH("默认路径");
        else
            workerEntity.setWKIMAGEPATH(workerDto.getImagepath());
        iWorkerDao.AddWorker(workerEntity);

        for (LicencesInfo licencesInfo: workerDto.getLicences()) {
            LicenceEntity licenceEntity = new LicenceEntity();
            licenceEntity.setWID(workerDto.getWid());
            licenceEntity.setLCNAME(licencesInfo.getLcname());
            licenceEntity.setLCDATE(licencesInfo.getLcdate());
            iWorkerDao.AddLicenceInfo(licenceEntity);
        }

        for (TagInfo tagInfo: workerDto.getTags()) {
            TagEntity tagEntity = new TagEntity();
            tagEntity.setTID(tagInfo.getTid());
            tagEntity.setTAGNAME(tagInfo.getTagname());
            tagEntity.setWID(workerDto.getWid());
            iWorkerDao.AddTag(tagEntity);
        }

        baseDto.setCode(0);
        baseDto.setMessage("添加用户成功");
        return baseDto;
    }

    @Transactional
    @Override
    public WorkersDto getWorkers(Integer uid) {
        List<WorkerEntity> workerEntityList = iWorkerDao.GetWorkersInfo(uid);
        if (workerEntityList.size() == 0){
            workersDto.setCode(1);
            workersDto.setMessage("未获取到用户信息");
            return workersDto;
        }

        List<WorkerBaseInfo> workerBaseInfoList = new ArrayList<WorkerBaseInfo>();
        for (WorkerEntity workerEntity:workerEntityList) {
            WorkerBaseInfo workerBaseInfo = new WorkerBaseInfo();
            workerBaseInfo.setWid(workerEntity.getWID());
            workerBaseInfo.setChname(workerEntity.getWKCHNAME());
            workerBaseInfo.setSurname(workerEntity.getWKSURNAME());
            workerBaseInfo.setEnname(workerEntity.getWKENNAME());
            workerBaseInfo.setImagepath(workerEntity.getWKIMAGEPATH());
            workerBaseInfoList.add(workerBaseInfo);
        }
        workersDto.setCode(0);
        workersDto.setMessage("成功获取到用户信息");
        workersDto.setMember(workerBaseInfoList);
        return workersDto;
    }

    @Transactional
    @Override
    public WorkerDto getWorker(Integer uid, String wid) {

        WorkerEntity workerEntity = iWorkerDao.GetWorkerInfo(wid);
        if (null == workerEntity){
            workerDto.setCode(1);
            workerDto.setMessage("获取用户详细信息失败");
            return workerDto;
        }
        List<LicenceEntity> licenceEntityList = iWorkerDao.GetLicenceInfoByWID(wid);
        if (licenceEntityList.size() == 0){
            workerDto.setCode(1);
            workerDto.setMessage("获取用户详细信息失败");
            return workerDto;
        }
        List<TagEntity> tagEntityList = iWorkerDao.GetTagsInfoByWID(wid);
        if (tagEntityList.size() == 0){
            workerDto.setCode(1);
            workerDto.setMessage("获取用户详细信息失败");
            return workerDto;
        }

        workerDto.setCode(0);
        workerDto.setMessage("获取用户详细信息成功");

        workerDto.setWid(workerEntity.getWID());
        workerDto.setChname(workerEntity.getWKCHNAME());
        workerDto.setSurname(workerEntity.getWKSURNAME());
        workerDto.setEnname(workerEntity.getWKENNAME());
        workerDto.setWkcard(workerEntity.getWKCARD());
        workerDto.setCcrsid(workerEntity.getWKCCRSID());
        workerDto.setOther(workerEntity.getWKOTHER());
        workerDto.setImagepath(workerEntity.getWKIMAGEPATH());

        List<LicencesInfo> licencesInfoList = new ArrayList<LicencesInfo>();
        for (LicenceEntity licenceEntity:licenceEntityList) {
            LicencesInfo licencesInfo = new LicencesInfo();
            licencesInfo.setLcname(licenceEntity.getLCNAME());
            licencesInfo.setLcdate(licenceEntity.getLCDATE());
            licencesInfoList.add(licencesInfo);
        }
        workerDto.setLicences(licencesInfoList);

        List<TagInfo> tagInfoList = new ArrayList<TagInfo>();
        for (TagEntity tagEntity:tagEntityList) {
            TagInfo tagInfo = new TagInfo();
            tagInfo.setTid(tagEntity.getTID());
            tagInfo.setTagname(tagEntity.getTAGNAME());
            tagInfoList.add(tagInfo);
        }
        workerDto.setTags(tagInfoList);

        return workerDto;
    }

    @Transactional
    @Override
    public BaseDto putWorker(Integer uid, String wid, Map<String, String> var) {
        return baseDto;
    }

    @Transactional
    @Override
    public BaseDto deleteWorker(Integer uid, String wid) {
        iWorkerDao.DeleteWorkerByWID(wid);
        iWorkerDao.DeleteLicenceByWID(wid);
        iWorkerDao.DeleteTagByWID(wid);
        baseDto.setCode(0);
        baseDto.setMessage("删除用户成功");
        return baseDto;
    }

    @Override
    public BaseDto putHeadico(Map<String, String> var) {
        return null;
    }
}
