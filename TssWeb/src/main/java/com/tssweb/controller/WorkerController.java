package com.tssweb.controller;

import com.tssweb.dto.BaseDto;
import com.tssweb.dto.WorkerDto;
import com.tssweb.dto.WorkersDto;
import com.tssweb.service.IWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by xiaodj on 2018/11/28.
 */
@Controller
public class WorkerController {

    @Autowired
    private IWorkerService iWorkerService;

    /**
     * 添加员工信息
     */
    @RequestMapping(value = "/v1/user/{uid}/worker", method = RequestMethod.POST)
    public @ResponseBody BaseDto AddWorker(@PathVariable("uid") Integer uid, @RequestBody WorkerDto workerDto){
        return iWorkerService.addWorker(uid, workerDto);
    }

    /**
     * 删除员工信息
     */
    @RequestMapping(value = "/v1/user/{uid}/worker/{wid}", method = RequestMethod.DELETE)
    public @ResponseBody BaseDto DeleteWorker(@PathVariable("uid") Integer uid, @PathVariable("wid") String wid){
        return iWorkerService.deleteWorker(uid, wid);
    }

    /**
     * 修改员工信息
     */
    @RequestMapping(value = "/v1/user/{uid}/worker/{wid}", method = RequestMethod.PUT)
    public @ResponseBody BaseDto PutWorker(@PathVariable("uid") Integer uid,
                                           @PathVariable("wid") String wid,
                                           @RequestBody WorkerDto workerDto){
        return iWorkerService.putWorker(uid, wid, workerDto);
    }

    /**
     * 获取员工详细信息
     */
    @RequestMapping(value = "/v1/user/{uid}/worker/{wid}", method = RequestMethod.GET)
    public @ResponseBody
    WorkerDto GetWorker(@PathVariable("uid") Integer uid, @PathVariable("wid") String wid){
        return iWorkerService.getWorker(uid, wid);
    }

    /**
     * 获取员工基本信息
     */
    @RequestMapping(value = "/v1/user/{uid}/workers/swid/{swid}/ewid/{ewid}/tid/{tid}", method = RequestMethod.GET)
    public @ResponseBody
    WorkersDto GetWorkers(@PathVariable("uid") Integer uid,@PathVariable("swid") String swid,
                                     @PathVariable("ewid") String ewid, @PathVariable("tid") String tid){
        return iWorkerService.getWorkers(uid, swid, ewid, tid);
    }

    /**
     * 上传员工头像
     */
//    @RequestMapping(value = "/api/records", method = RequestMethod.GET)
//    public @ResponseBody BaseDto GetRecords(@RequestBody Map<String, String> param){
//        return iRecordService.getRecords(param);
//    }
}
