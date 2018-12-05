package com.tssweb.controller;

import com.tssweb.dto.BaseDto;
import com.tssweb.dto.RecordsDto;
import com.tssweb.service.IRecordService;
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
public class RecordController {

    private IRecordService iRecordService;
    /**
     * 查询记录
     * @author 邓江
     */
    @RequestMapping(value = "/api/records", method = RequestMethod.GET)
    public @ResponseBody
    RecordsDto GetRecords(@RequestBody Map<String, String> param){
        return iRecordService.getRecords(param);
    }

}
