package com.tssweb.service.impl;

import com.tssweb.dao.IRecordDao;
import com.tssweb.dao.IWorkerDao;
import com.tssweb.dto.*;
import com.tssweb.entity.LicenceEntity;
import com.tssweb.entity.TagEntity;
import com.tssweb.entity.WorkerEntity;
import com.tssweb.entity.WorkersEntity;
import com.tssweb.netty.DataEngine;
import com.tssweb.service.IWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.tssweb.netty.DataEngine.dataMap;

@Service
public class WorkerServiceImpl implements IWorkerService {

    @Autowired
    private IWorkerDao iWorkerDao;
    @Autowired
    private IRecordDao iRecordDao;
    @Autowired
    private BaseDto baseDto;
    @Autowired
    private WorkerDto workerDto;
    @Autowired
    private WorkersDto workersDto;

    @Transactional
    @Override
    public BaseDto addWorker(Integer uid, WorkerDto workerDto) {
        //判断员工是否存在
        WorkerEntity workerEntity = iWorkerDao.GetWorkerInfo(workerDto.getWid());
        if(workerEntity != null){
            baseDto.setCode(1);
            baseDto.setMessage("员工编号已存在");
            return baseDto;
        }

        //判断标签是否已存在
        for (TagInfo tagInfo: workerDto.getTags()) {
            TagEntity tagEntity = iWorkerDao.GetTagInfoByTID(tagInfo.getTid());
            if (tagEntity != null){
                baseDto.setCode(1);
                baseDto.setMessage("标签编号已存在");
                return baseDto;
            }
        }

        workerEntity = new WorkerEntity();
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
    public WorkersDto getWorkers(Integer uid, String swid, String ewid, String tid) {

        List<WorkerEntity> workerEntityList = new ArrayList<WorkerEntity>();
        if (swid.isEmpty() && ewid.isEmpty() && !tid.isEmpty())
            workerEntityList = iWorkerDao.GetWorkersInfoByTid(uid, tid);
        if (!swid.isEmpty() && !ewid.isEmpty() && tid.isEmpty())
            workerEntityList = iWorkerDao.GetWorkersInfoByWid(uid, swid, ewid);
        if (!swid.isEmpty() && !ewid.isEmpty() && !tid.isEmpty())
            workerEntityList = iWorkerDao.GetWorkersInfoByWidAndTid(uid, swid, ewid, tid);

        if (workerEntityList.size() == 0){
            workersDto.setCode(1);
            workersDto.setMessage("未获取到用户信息");
            return workersDto;
        }

        List<WorkerInfo> workerBaseInfoList = new ArrayList<WorkerInfo>();
        for (WorkerEntity workerEntity:workerEntityList) {
            WorkerInfo workerInfo = new WorkerInfo();
            workerInfo.setWid(workerEntity.getWID());
            workerInfo.setChname(workerEntity.getWKCHNAME());
            workerInfo.setSurname(workerEntity.getWKSURNAME());
            workerInfo.setEnname(workerEntity.getWKENNAME());
            workerBaseInfoList.add(workerInfo);
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

    //@Transactional
    @Override
    public BaseDto putWorker(Integer uid, String wid, WorkerDto workerDto) {
        List<TagEntity> tagEntityList = iWorkerDao.GetTagsInfoByWID(wid);

        WorkerEntity workerEntity = new WorkerEntity();
        workerEntity.setWID(wid);
        workerEntity.setWKCHNAME(workerDto.getChname());
        workerEntity.setWKSURNAME(workerDto.getSurname());
        workerEntity.setWKENNAME(workerDto.getEnname());
        workerEntity.setWKCARD(workerDto.getWkcard());
        workerEntity.setWKCCRSID(workerDto.getCcrsid());
        workerEntity.setWKOTHER(workerDto.getOther());
        iWorkerDao.UpdateWorker(workerEntity);

        iWorkerDao.DeleteLicenceByWID(wid);
        iWorkerDao.DeleteTagByWID(wid);
        for (LicencesInfo licencesInfo: workerDto.getLicences()) {
            LicenceEntity licenceEntity = new LicenceEntity();
            licenceEntity.setWID(wid);
            licenceEntity.setLCNAME(licencesInfo.getLcname());
            licenceEntity.setLCDATE(licencesInfo.getLcdate());
            iWorkerDao.AddLicenceInfo(licenceEntity);
        }

        for (TagInfo tagInfo: workerDto.getTags()) {
            TagEntity tagEntity = new TagEntity();
            tagEntity.setTID(tagInfo.getTid());
            tagEntity.setTAGNAME(tagInfo.getTagname());
            tagEntity.setWID(wid);
            iWorkerDao.AddTag(tagEntity);
        }

        synchronized (dataMap) {
            DataEngine dataEngine = new DataEngine();
            for (TagEntity tagEntity : tagEntityList) {
                String tid = tagEntity.getTID();
                dataEngine.CatchUpdateByTid(tid, workerDto);
            }
        }

        baseDto.setCode(0);
        baseDto.setMessage("修改用户成功");
        return baseDto;
    }

    @Transactional
    @Override
    public BaseDto deleteWorker(Integer uid, String wid) {
        List<TagEntity> tagEntityList = iWorkerDao.GetTagsInfoByWID(wid);

        if(iWorkerDao.DeleteWorkerByWID(wid) < 0){
            baseDto.setCode(1);
            baseDto.setMessage("删除用户失败");
            return baseDto;
        }

        if(iWorkerDao.DeleteTagByWID(wid) < 0){
            baseDto.setCode(1);
            baseDto.setMessage("删除用户失败");
            return baseDto;
        }

        if(iWorkerDao.DeleteLicenceByWID(wid) < 0){
            baseDto.setCode(1);
            baseDto.setMessage("删除用户失败");
            return baseDto;
        }
        synchronized (dataMap) {
            DataEngine dataEngine = new DataEngine();
            for (TagEntity tagEntity : tagEntityList) {
                String tid = tagEntity.getTID();
                dataEngine.CatchRemoveTID(tid);
            }

            if(iRecordDao.DeleteRecordByWID(wid) < 0){
                baseDto.setCode(1);
                baseDto.setMessage("删除用户失败");
                return baseDto;
            }
        }

        baseDto.setCode(0);
        baseDto.setMessage("删除用户成功");
        return baseDto;
    }

    @Override
    public BaseDto putHeadico(Map<String, String> var) {
        return null;
    }
}
