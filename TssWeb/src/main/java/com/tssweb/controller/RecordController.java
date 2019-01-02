package com.tssweb.controller;

import com.tssweb.dto.BaseDto;
import com.tssweb.dto.RecordsDto;
import com.tssweb.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by xiaodj on 2018/11/28.
 */
@Controller
public class RecordController {

    @Autowired
    private IRecordService iRecordService;
    @Autowired
    private RecordsDto recordsDto;
    /**
     * 查询记录
     * @author 邓江
     */
    @RequestMapping(value = "/v1/user/{uid}/records/date/{sdt}/{edt}", method = RequestMethod.GET)
    public @ResponseBody
    RecordsDto GetRecords(@PathVariable("uid") Integer uid, @PathVariable("sdt") String sdt, @PathVariable("edt") String edt){
        try{
            recordsDto = iRecordService.getRecords(uid, "", sdt, edt);
        }catch (Exception e){
            recordsDto.setCode(1);
            recordsDto.setMessage("服务异常");
        }
        return recordsDto;
    }

    /**
     * 查询记录
     * @author 邓江
     */
    @RequestMapping(value = "/v1/user/{uid}/worker/{wid}/records", method = RequestMethod.GET)
    public @ResponseBody
    RecordsDto GetRecords(@PathVariable("uid") Integer uid, @PathVariable("wid") String wid){
        try{
            recordsDto = iRecordService.getRecords(uid, wid, "", "");
        }catch (Exception e){
            recordsDto.setCode(1);
            recordsDto.setMessage("服务异常");
        }
        return recordsDto;
    }

    /**
     * 查询记录
     * @author 邓江
     */
    @RequestMapping(value = "/v1/user/{uid}/worker/{wid}/records/date/{sdt}/{edt}", method = RequestMethod.GET)
    public @ResponseBody
    RecordsDto GetRecords(@PathVariable("uid") Integer uid, @PathVariable("wid") String wid,
                          @PathVariable("sdt") String sdt, @PathVariable("edt") String edt){
        try{
            recordsDto = iRecordService.getRecords(uid, wid, sdt, edt);
        }catch (Exception e){
            recordsDto.setCode(1);
            recordsDto.setMessage("服务异常");
        }
        return recordsDto;
    }
}
