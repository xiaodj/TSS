package com.tssweb.controller;

import com.tssweb.dto.BaseDto;
import com.tssweb.dto.WorkerDto;
import com.tssweb.dto.WorkersDto;
import com.tssweb.service.IWorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by xiaodj on 2018/11/28.
 */
@Controller
public class WorkerController {

    private IWorkerService iWorkerService;

    /**
     * 添加员工信息
     */
    @RequestMapping(value = "/api/worker", method = RequestMethod.POST)
    public @ResponseBody BaseDto AddWorker(@RequestBody Map<String, String> param){
        return iWorkerService.addWorker(param);
    }

    /**
     * 删除员工信息   /api/worker/delete
     */
    @RequestMapping(value = "/api/worker", method = RequestMethod.DELETE)
    public @ResponseBody BaseDto DeleteWorker(@RequestBody Map<String, String> param){
        return iWorkerService.deleteWorker(param);
    }

    /**
     * 修改员工信息   /api/worker/update
     */
    @RequestMapping(value = "/api/worker", method = RequestMethod.PUT)
    public @ResponseBody BaseDto PutWorker(@RequestBody Map<String, String> param){
        return iWorkerService.putWorker(param);
    }

    /**
     * 获取员工详细信息 /api/workers
     */
    @RequestMapping(value = "/api/worker", method = RequestMethod.GET)
    public @ResponseBody
    WorkerDto GetWorker(@RequestBody Map<String, String> param){
        return iWorkerService.getWorker(param);
    }

    /**
     * 获取员工基本信息
     */
    @RequestMapping(value = "/api/workers", method = RequestMethod.GET)
    public @ResponseBody
    WorkersDto GetWorkers(@RequestBody Map<String, String> param){
        return iWorkerService.getWorkers(param);
    }

    /**
     * 上传员工头像
     */
//    @RequestMapping(value = "/api/records", method = RequestMethod.GET)
//    public @ResponseBody BaseDto GetRecords(@RequestBody Map<String, String> param){
//        return iRecordService.getRecords(param);
//    }
}
