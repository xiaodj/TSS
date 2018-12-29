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
    /**
     * 查询记录
     * @author 邓江
     */
    @RequestMapping(value = "/v1/user/{uid}/records/date/{sdt}/{edt}", method = RequestMethod.GET)
    public @ResponseBody
    RecordsDto GetRecords(@PathVariable("uid") Integer uid, @PathVariable("sdt") String sdt, @PathVariable("edt") String edt){
        return iRecordService.getRecords(uid, "", sdt, edt);
    }

    /**
     * 查询记录
     * @author 邓江
     */
    @RequestMapping(value = "/v1/user/{uid}/worker/{wid}/records", method = RequestMethod.GET)
    public @ResponseBody
    RecordsDto GetRecords(@PathVariable("uid") Integer uid, @PathVariable("wid") String wid){
        return iRecordService.getRecords(uid, wid, "", "");
    }

    /**
     * 查询记录
     * @author 邓江
     */
    @RequestMapping(value = "/v1/user/{uid}/worker/{wid}/records/date/{sdt}/{edt}", method = RequestMethod.GET)
    public @ResponseBody
    RecordsDto GetRecords(@PathVariable("uid") Integer uid, @PathVariable("wid") String wid,
                          @PathVariable("sdt") String sdt, @PathVariable("edt") String edt){
        return iRecordService.getRecords(uid, wid, sdt, edt);
    }
}
