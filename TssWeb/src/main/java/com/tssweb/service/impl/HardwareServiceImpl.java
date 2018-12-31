package com.tssweb.service.impl;

import com.tssweb.dao.IHardwareDao;
import com.tssweb.dto.BaseDto;
import com.tssweb.dto.HardwareSetDto;
import com.tssweb.entity.HardwareSetEntity;
import com.tssweb.netty.DataEngine;
import com.tssweb.netty.NettyOutHandler;
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

    @Override
    public BaseDto putDeviceSensitivity(Integer uid, Map<String, String> var) {
        //获取
        String strSensi = var.get("sensitivity");
        //发送设置命令
        byte[] bytes = new byte[7];
        bytes[0] = 0x0A;
        bytes[1] = 0x64;
        bytes[2] = 0x04;
        bytes[3] = 0x25;
        bytes[4] = (byte)Integer.valueOf(strSensi).intValue();
        bytes[5] = 0x02;
        bytes[6] = DataEngine.CheckSum(bytes, 6);
        if(!NettyOutHandler.sendMsg(uid, bytes)){
            baseDto.setCode(1);
            baseDto.setMessage("设置FRID 灵敏度失败");
            return baseDto;
        }
        //成功则入库
        HardwareSetEntity hardwareSetEntity = iHardwareDao.GetHardwareSetByUID(uid);
        if (null == hardwareSetEntity){
            hardwareSetEntity = new HardwareSetEntity();
            hardwareSetEntity.setFRSPEED("");
            hardwareSetEntity.setFRRANGE(strSensi);
            hardwareSetEntity.setFRSTATE(0);
            hardwareSetEntity.setUID(uid);
            iHardwareDao.AddHardwareSet(hardwareSetEntity);
        } else {
            //hardwareSetEntity.setFRSPEED("");
            hardwareSetEntity.setFRRANGE(strSensi);
            //hardwareSetEntity.setFRSTATE(0);
            iHardwareDao.UpdateHardwareSetByUID(hardwareSetEntity);
        }
        baseDto.setCode(0);
        baseDto.setMessage("灵敏度设置成功");
        return baseDto;
    }

    @Override
    public BaseDto putDeviceTagIdentify(Integer uid, Map<String, String> var) {
        String strStatus = var.get("tagidentify");
        if (strStatus.equals("1")){
            byte[] bytes = new byte[5];
            bytes[0] = 0x0A;
            bytes[1] = 0x64;
            bytes[2] = 0x02;
            bytes[3] = (byte) 0x90;
            bytes[4] = DataEngine.CheckSum(bytes, 4);
            if(!NettyOutHandler.sendMsg(uid, bytes)){
                baseDto.setCode(1);
                baseDto.setMessage("设置FRID 开启标签识别失败");
                return baseDto;
            }
        }else if (strStatus.equals("0")){
            byte[] bytes = new byte[5];
            bytes[0] = 0x0A;
            bytes[1] = 0x64;
            bytes[2] = 0x02;
            bytes[3] = (byte) 0x91;
            bytes[4] = DataEngine.CheckSum(bytes, 4);
            if(!NettyOutHandler.sendMsg(uid, bytes)){
                baseDto.setCode(1);
                baseDto.setMessage("设置FRID 停止标签识别失败");
                return baseDto;
            }
        }else {
            baseDto.setCode(1);
            baseDto.setMessage("无效的状态");
            return baseDto;
        }

        //成功  则入库
        HardwareSetEntity hardwareSetEntity = iHardwareDao.GetHardwareSetByUID(uid);
        if (null == hardwareSetEntity){
            hardwareSetEntity = new HardwareSetEntity();
            //hardwareSetEntity.setGID("001");
            hardwareSetEntity.setFRSPEED("");
            hardwareSetEntity.setFRRANGE("");
            hardwareSetEntity.setFRSTATE(Integer.valueOf(strStatus));
            hardwareSetEntity.setUID(uid);
            iHardwareDao.AddHardwareSet(hardwareSetEntity);
        } else {
            //hardwareSetEntity.setFRSPEED(var.get("frspeed"));
            //hardwareSetEntity.setFRRANGE(var.get("frrange"));
            hardwareSetEntity.setFRSTATE(Integer.valueOf(strStatus));
            iHardwareDao.UpdateHardwareSetByUID(hardwareSetEntity);
        }
        baseDto.setCode(0);
        baseDto.setMessage("标签识别状态更改成功");
        return baseDto;
    }
}
