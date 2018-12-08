package com.tssweb.service.impl;

import com.tssweb.dao.IHardwareDao;
import com.tssweb.dto.BaseDto;
import com.tssweb.dto.HardwareSetDto;
import com.tssweb.entity.HardwareSetEntity;
import com.tssweb.service.IHardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
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
        if (null == hardwareSetEntity){
            hardwareSetDto.setCode(1);
            hardwareSetDto.setMessage("未获取到硬件信息");
            return hardwareSetDto;
        }else {
            hardwareSetDto.setCode(0);
            hardwareSetDto.setMessage("获取硬件信息成功");
            hardwareSetDto.setFrSpeed(hardwareSetEntity.getFRSPEED());
            hardwareSetDto.setFrRange(hardwareSetEntity.getFRRANGE());
            hardwareSetDto.setFrState(hardwareSetEntity.getFRSTATE());
            return hardwareSetDto;
        }
    }

    @Override
    public BaseDto putHardwareInfo(Integer uid, Map<String, String> var) {
        HardwareSetEntity hardwareSetEntity = iHardwareDao.GetHardwareSetByUID(uid);
        if (null == hardwareSetEntity){
            hardwareSetEntity = new HardwareSetEntity();
            hardwareSetEntity.setGID("001");
            hardwareSetEntity.setFRSPEED(var.get("frspeed"));
            hardwareSetEntity.setFRRANGE(var.get("frrange"));
            hardwareSetEntity.setFRSTATE(Integer.valueOf(var.get("frstate")));
            hardwareSetEntity.setUID(uid);
            iHardwareDao.AddHardwareSet(hardwareSetEntity);
        } else {
            hardwareSetEntity.setFRSPEED(var.get("frspeed"));
            hardwareSetEntity.setFRRANGE(var.get("frrange"));
            hardwareSetEntity.setFRSTATE(Integer.valueOf(var.get("frstate")));
            iHardwareDao.UpdateHardwareSetByUID(hardwareSetEntity);
        }
        baseDto.setCode(0);
        baseDto.setMessage("修改硬件信息成功");
        return baseDto;
    }
}
