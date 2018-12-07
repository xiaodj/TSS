package com.tssweb.service.impl;

import com.tssweb.dao.IHardwareDao;
import com.tssweb.dto.BaseDto;
import com.tssweb.dto.HardwareSetDto;
import com.tssweb.entity.HardwareSetEntity;
import com.tssweb.service.IHardwareService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class HardwareServiceImpl implements IHardwareService {
    @Autowired
    private IHardwareDao iHardwareDao;

    @Autowired
    private HardwareSetDto hardwareSetDto;
    @Autowired
    private BaseDto baseDto;

    @Override
    public HardwareSetDto getHardwareInfo(Integer uid) {
        HardwareSetEntity hardwareSetEntity = iHardwareDao.GetHardwareSetByUID(uid);
        if (hardwareSetEntity.equals(null)){
            return hardwareSetDto;
        }else {
            return hardwareSetDto;
        }
    }

    @Override
    public BaseDto putHardwareInfo(Map<String, String> var) {
        baseDto.setCode(0);
        baseDto.setMessage("修改硬件信息成功");
        return baseDto;
    }
}
