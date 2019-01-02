package com.tssweb.timer;

import com.alibaba.fastjson.JSONObject;
import com.tssweb.dao.IRecordDao;
import com.tssweb.dao.IUserDao;
import com.tssweb.dto.RealDataDto;
import com.tssweb.entity.LicenceEntity;
import com.tssweb.entity.RecordEntity;
import com.tssweb.entity.UserSetEntity;
import com.tssweb.netty.CacheData;
import com.tssweb.utils.SpringContextHolder;
import com.tssweb.websocket.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.tssweb.netty.DataEngine.dataMap;
import static com.tssweb.netty.DataEngine.uid;

@Component
public class OutTask extends TimerTask {

    private IRecordDao iRecordDao = SpringContextHolder.getBean(IRecordDao.class);
    private IUserDao   iUserDao = SpringContextHolder.getBean(IUserDao.class);

    static public Integer timeout;

    public OutTask(){
        UserSetEntity userSetEntity = iUserDao.GetUserSetInfoByUID(1);
        timeout = userSetEntity.getTIMEOUT();
    }

    public static Integer getTimeout() {
        return timeout;
    }

    public static void setTimeout(Integer timeout) {
        OutTask.timeout = timeout;
    }

    @Override
    public void run() {
        Boolean bSendFlag = false;
        List<RealDataDto> realDataDtoList = new ArrayList<RealDataDto>();
        synchronized (dataMap){
            Iterator iter = dataMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String tagid = (String) entry.getKey();
                CacheData cacheData = (CacheData) entry.getValue();
                if (cacheData.getStartdate() == null)
                    continue;
                Date currentDT = new Date();
                long spantime = (currentDT.getTime() - cacheData.getUpdate().getTime()) / 1000;
                if (spantime >= timeout) {   //已离开
                    cacheData.setStartdate(null);
                    cacheData.setUpdate(currentDT);
                    RecordEntity recordEntity = new RecordEntity();
                    recordEntity.setWID(cacheData.getWid());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                    recordEntity.setRCDATE(dateFormat.format(currentDT));
                    recordEntity.setRCTIME(timeFormat.format(currentDT));
                    recordEntity.setTAGSTATE(1);
                    iRecordDao.AddRecord(recordEntity);
                    //推送
                    bSendFlag = true;
                } else {
                    RealDataDto realDataDto = new RealDataDto();
                    realDataDto.setUsername(cacheData.getUsername());
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                    realDataDto.setIntime(timeFormat.format(cacheData.getStartdate()));
                    for (LicenceEntity licenceEntity : cacheData.getLcList()) {
                        if (licenceEntity.getLCNAME().equals("绿卡"))
                            realDataDto.setLc1(licenceEntity.getLCDATE());
                        else if (licenceEntity.getLCNAME().equals("密卡"))
                            realDataDto.setLc2(licenceEntity.getLCDATE());
                        else if (licenceEntity.getLCNAME().equals("CP"))
                            realDataDto.setLc3(licenceEntity.getLCDATE());
                    }
                    realDataDtoList.add(realDataDto);
                }
            }
        }
        if (bSendFlag){ //推送至前端
            Collections.sort(realDataDtoList);
            MessageHandler messageHandler = new MessageHandler();
            messageHandler.sendMessageToUser(uid, JSONObject.toJSONString(realDataDtoList));
        }
    }
}
