package com.tssweb.service;

import com.tssweb.dto.BaseDto;
import com.tssweb.dto.WorkerDto;
import com.tssweb.dto.WorkersDto;

import java.util.Map;

public interface IWorkerService {

    /**
     * 添加员工信息
     */
    public BaseDto addWorker(Integer uid, WorkerDto workerDto);

    /**
     * 查询员工基本信息
     */
    public WorkersDto getWorkers(Integer uid);

    /**
     * 查询员工详细信息
     */
    public WorkerDto getWorker(Integer uid, String wid);

    /**
     * 修改员工信息
     */
    public BaseDto putWorker(Integer uid, String wid, WorkerDto workerDto);

    /**
     * 删除员工信息
     */
    public BaseDto deleteWorker(Integer uid, String wid);

    /**
     * 上传员工头像ico图标
     */
    public BaseDto putHeadico(Map<String, String> var);

}
