package com.tssweb.service;

import com.tssweb.dto.BaseDto;

import java.util.Map;

public interface IWorkerService {

    /**
     * 添加员工信息
     */
    public BaseDto addWorker(Map<String, String> var);

    /**
     * 查询员工基本信息
     */
    public BaseDto getWorkers(Map<String, String> var);

    /**
     * 查询员工详细信息
     */
    public BaseDto getWorker(Map<String, String> var);

    /**
     * 修改员工信息
     */
    public BaseDto updateWorker(Map<String, String> var);

    /**
     * 删除员工信息
     */
    public BaseDto deleteWorker(Map<String, String> var);

    /**
     * 上传员工头像ico图标
     */
    public BaseDto updateHeadico(Map<String, String> var);

}
