package com.tssweb.controller;

import com.tssweb.dto.BaseDto;
import com.tssweb.dto.HardwareSetDto;
import com.tssweb.service.IHardwareService;
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
public class HardwareController {

    private IHardwareService iHardwareService;

    /**
     * 获取硬件设置信息 /api/system/update
     */
    @RequestMapping(value = "/api/hardware/set", method = RequestMethod.GET)
    public @ResponseBody
    HardwareSetDto GetHardwareInfo(@RequestBody Map<String, String> param){
        return iHardwareService.getHardwareInfo(param);
    }

    /**
     * 修改硬件设置信息   /api/system
     */
    @RequestMapping(value = "/api/hardware/set", method = RequestMethod.PUT)
    public @ResponseBody BaseDto PutHardwareInfo(@RequestBody Map<String, String> param){
        return iHardwareService.putHardwareInfo(param);
    }
}
