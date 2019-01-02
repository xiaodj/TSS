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
    @Autowired
    private BaseDto baseDto;
    @Autowired
    private WorkerDto workerDto;
    @Autowired
    private WorkersDto workersDto;
    /**
     * 添加员工信息
     */
    @RequestMapping(value = "/v1/user/{uid}/worker", method = RequestMethod.POST)
    public @ResponseBody BaseDto AddWorker(@PathVariable("uid") Integer uid, @RequestBody WorkerDto workerDto){
        try{
            baseDto = iWorkerService.addWorker(uid, workerDto);
        }catch (Exception e){
            baseDto.setCode(1);
            baseDto.setMessage("服务发生异常");
        }

        return baseDto;
    }

    /**
     * 删除员工信息
     */
    @RequestMapping(value = "/v1/user/{uid}/worker/{wid}", method = RequestMethod.DELETE)
    public @ResponseBody BaseDto DeleteWorker(@PathVariable("uid") Integer uid, @PathVariable("wid") String wid){
        try{
            baseDto = iWorkerService.deleteWorker(uid, wid);
        }catch (Exception e){
            baseDto.setCode(1);
            baseDto.setMessage("服务发生异常");
        }
        return baseDto;
    }

    /**
     * 修改员工信息
     */
    @RequestMapping(value = "/v1/user/{uid}/worker/{wid}", method = RequestMethod.PUT)
    public @ResponseBody BaseDto PutWorker(@PathVariable("uid") Integer uid,
                                           @PathVariable("wid") String wid,
                                           @RequestBody WorkerDto workerDto){
        try{
            baseDto = iWorkerService.putWorker(uid, wid, workerDto);
        }catch (Exception e){
            baseDto.setCode(1);
            baseDto.setMessage("服务发生异常");
        }
        return baseDto;
    }

    /**
     * 获取员工详细信息
     */
    @RequestMapping(value = "/v1/user/{uid}/worker/{wid}", method = RequestMethod.GET)
    public @ResponseBody
    WorkerDto GetWorker(@PathVariable("uid") Integer uid, @PathVariable("wid") String wid){
        try{
            workerDto = iWorkerService.getWorker(uid, wid);
        }catch (Exception e){
            workerDto.setCode(1);
            workerDto.setMessage("服务发生异常");
        }
        return workerDto;
    }

    /**
     * 获取员工基本信息
     */
    @RequestMapping(value = "/v1/user/{uid}/workers/tid/{tid}", method = RequestMethod.GET)
    public @ResponseBody WorkersDto GetWorkersByTid(@PathVariable("uid") Integer uid,@PathVariable("tid") String tid){
        try{
            workersDto = iWorkerService.getWorkers(uid, "", "", tid);
        }catch (Exception e){
            workersDto.setCode(1);
            workersDto.setMessage("服务发生异常");
        }
        return workersDto;
    }
    @RequestMapping(value = "/v1/user/{uid}/workers/wid/{swid}/{ewid}", method = RequestMethod.GET)
    public @ResponseBody WorkersDto GetWorkersByWid(@PathVariable("uid") Integer uid
            ,@PathVariable("swid") String swid, @PathVariable("ewid") String ewid){
        try{
            workersDto = iWorkerService.getWorkers(uid, swid, ewid, "");
        }catch (Exception e){
            workersDto.setCode(1);
            workersDto.setMessage("服务发生异常");
        }
        return workersDto;
    }
    @RequestMapping(value = "/v1/user/{uid}/workers/wid/{swid}/{ewid}/tid/{tid}", method = RequestMethod.GET)
    public @ResponseBody
    WorkersDto GetWorkersByWidsTid(@PathVariable("uid") Integer uid,@PathVariable("swid") String swid,
                          @PathVariable("ewid") String ewid, @PathVariable("tid") String tid){
        try{
            workersDto = iWorkerService.getWorkers(uid, swid, ewid, tid);
        }catch (Exception e){
            workersDto.setCode(1);
            workersDto.setMessage("服务发生异常");
        }
        return workersDto;
    }


    /**
     * 上传员工头像
     */
//    @RequestMapping(value = "/api/records", method = RequestMethod.GET)
//    public @ResponseBody BaseDto GetRecords(@RequestBody Map<String, String> param){
//        return iRecordService.getRecords(param);
//    }
}
