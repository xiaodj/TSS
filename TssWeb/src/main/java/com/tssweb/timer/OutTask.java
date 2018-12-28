package com.tssweb.timer;

import com.alibaba.fastjson.JSONObject;
import com.tssweb.dao.IRecordDao;
import com.tssweb.dto.RealDataDto;
import com.tssweb.entity.LicenceEntity;
import com.tssweb.entity.RecordEntity;
import com.tssweb.netty.CacheData;
import com.tssweb.websocket.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.tssweb.netty.DataEngine.dataMap;
import static com.tssweb.netty.DataEngine.uid;

@Component
public class OutTask extends TimerTask {

    @Autowired
    private IRecordDao iRecordDao;

    private Integer timeout;

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    @Override
    public void run() {
        Boolean bSendFlag = false;
        List<RealDataDto> realDataDtoList = new ArrayList<RealDataDto>();
        Iterator iter = dataMap.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry entry = (Map.Entry)iter.next();
            String tagid = (String) entry.getKey();
            CacheData cacheData = (CacheData) entry.getValue();
            if (cacheData.getStartdate() == null)
                continue;
            Date currentDT = new Date();
            long spantime = (currentDT.getTime() - cacheData.getUpdate().getTime())/1000;
            if (spantime >= timeout){   //已离开
                cacheData.setStartdate(null);
                cacheData.setUpdate(currentDT);
                RecordEntity recordEntity = new RecordEntity();
                recordEntity.setWID(tagid);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                recordEntity.setRCDATE(dateFormat.format(currentDT));
                recordEntity.setRCTIME(timeFormat.format(currentDT));
                recordEntity.setTAGSTATE(1);
                iRecordDao.AddRecord(recordEntity);
                //推送
                bSendFlag = true;
            }else {
                RealDataDto realDataDto = new RealDataDto();
                realDataDto.setUsername(cacheData.getUsername());
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                realDataDto.setIntime(timeFormat.format(cacheData.getStartdate()));
                for (LicenceEntity licenceEntity: cacheData.getLcList()) {
                    if (licenceEntity.getLCNAME() == "绿卡到期日期")
                        realDataDto.setLc1(licenceEntity.getLCDATE());
                    else if (licenceEntity.getLCNAME() == "密卡到期日期")
                        realDataDto.setLc2(licenceEntity.getLCDATE());
                    else if (licenceEntity.getLCNAME() == "CP到期日期")
                        realDataDto.setLc3(licenceEntity.getLCDATE());
                }
                realDataDtoList.add(realDataDto);
            }
        }

        if (bSendFlag){ //推送至前端
            MessageHandler messageHandler = new MessageHandler();
            messageHandler.sendMessageToUser(uid, JSONObject.toJSONString(realDataDtoList));
        }
    }
}
