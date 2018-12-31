package com.tssweb.controller;

import com.tssweb.dto.BaseDto;
import com.tssweb.dto.HardwareSetDto;
import com.tssweb.service.IHardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by xiaodj on 2018/11/28.
 */
@Controller
public class HardwareController {

    @Autowired
    private IHardwareService iHardwareService;

    /**
     * 获取硬件设置信息 /api/system/update
     */
    @RequestMapping(value = "/v1/user/{uid}/hardware/set", method = RequestMethod.GET)
    public @ResponseBody
    HardwareSetDto GetHardwareInfo(@PathVariable("uid") Integer uid){
        return iHardwareService.getHardwareInfo(uid);
    }

    /**
     * 修改硬件设置信息   /api/system
     */
    @RequestMapping(value = "/v1/user/{uid}/hardware/set", method = RequestMethod.PUT)
    public @ResponseBody BaseDto PutHardwareInfo(@PathVariable("uid") Integer uid, @RequestBody Map<String, String> param){
        return iHardwareService.putHardwareInfo(uid, param);
    }

    /**
     * 修改设备灵敏度设置
     */
    @RequestMapping(value = "/v1/user/{uid}/device/sensitivity", method = RequestMethod.PUT)
    public @ResponseBody BaseDto PutSensitivity(@PathVariable("uid") Integer uid, @RequestBody Map<String, String> param){
        return iHardwareService.putDeviceSensitivity(uid, param);
    }

    /**
     * 修改设备标签识别状态
     */
    @RequestMapping(value = "/v1/user/{uid}/device/tagidentify", method = RequestMethod.PUT)
    public @ResponseBody BaseDto PutTagIdentify(@PathVariable("uid") Integer uid, @RequestBody Map<String, String> param){
        return iHardwareService.putDeviceTagIdentify(uid, param);
    }
}
