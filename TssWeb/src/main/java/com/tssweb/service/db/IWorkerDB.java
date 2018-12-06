package com.tssweb.service.db;

import com.tssweb.entity.LicenceEntity;
import com.tssweb.entity.TagEntity;
import com.tssweb.entity.WorkerEntity;

import java.util.List;

public interface IWorkerDB {

    /**
     * 通过员工编码判断员工是否存在
     */
    public boolean IsExistWorkerByWID(String wid);

    /**
     * 通过许可证编码判断许可证是否存在
     */
    public boolean IsExistLicenceByLID(String lid);

    /**
     * 通过标签编码判断标签是否存在
     */
    public boolean IsExistTagByTID(String tid);



    /**
     * 添加员工信息
     */
    public boolean AddWorker(WorkerEntity workerEntity);



    /**
     * 根据员工编码获取许可证信息
     */
    public List<LicenceEntity> GetLicenceInfoByWID(String wid);

    /**
     * 根据员工编码获取标签信息
     */
    public TagEntity GetTagInfoByWID(String wid);

    /**
     * 根据标签
     */
}
