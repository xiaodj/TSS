package com.tssweb.dao;

import com.tssweb.entity.LicenceEntity;
import com.tssweb.entity.TagEntity;
import com.tssweb.entity.WorkerEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWorkerDao {

    /**
     * 添加员工信息
     */
    public Integer AddWorker(WorkerEntity workerEntity);

    /**
     * 修改员工信息
     */
    public Integer UpdateWorker(WorkerEntity workerEntity);

    /**
     * 通过员工编码获取员工信息
     */
    public WorkerEntity GetWorkerInfo(String wid);

    /**
     * 通过用户编码获取员工信息集合
     */
    public List<WorkerEntity> GetWorkersInfo(@Param("uid") Integer uid, @Param("swid") String swid, @Param("ewid") String ewid, @Param("tid") String tid);

    /**
     * 通过员工编码删除员工信息
     */
    public Integer DeleteWorkerByWID(String wid);

    /**
     * 通过许可证编码获取许可证信息
     */
    public LicenceEntity GetLicenceInfoByLID(String lid);

    /**
     * 根据员工编码获取许可证信息
     */
    public List<LicenceEntity> GetLicenceInfoByWID(String wid);

    /**
     * 新增许可证信息
     */
    public Integer AddLicenceInfo(LicenceEntity licenceEntity);

    /**
     * 通过员工编号删除许可证信息
     */
    public Integer DeleteLicenceByWID(String wid);

    /**
     * 根据标签编码获取标签信息
     */
    public TagEntity GetTagInfoByTID(String tid);

    /**
     * 更加标签编码获取员工信息
     */
    public WorkerEntity GetWorkerInfoByTID(String tid);

    /**
     * 根据员工编码获取标签信息
     */
    public List<TagEntity> GetTagsInfoByWID(String wid);

    /**
     * 新增标签信息
     */
    public Integer AddTag(TagEntity tagEntity);

    /**
     * 通过员工编码删除标签信息
     */
    public Integer DeleteTagByWID(String wid);
}
