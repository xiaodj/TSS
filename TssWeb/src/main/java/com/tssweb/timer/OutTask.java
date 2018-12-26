package com.tssweb.timer;

import com.alibaba.fastjson.JSONObject;
import com.tssweb.dao.IRecordDao;
import com.tssweb.entity.RecordEntity;
import com.tssweb.netty.CacheData;
import com.tssweb.netty.DataEngine;
import com.tssweb.websocket.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import static com.tssweb.netty.DataEngine.cacheDataList;

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
        for (CacheData cache: cacheDataList) {
            String time = cache.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            try {
                Date rctime = sdf.parse(time);  //记录时间
                Date current = new Date();  //当前时间
                long spantime = (current.getTime() - rctime.getTime())/1000;
                if (spantime >= timeout){
                    //离开
                    Integer uid = DataEngine.wkMap.get(cache.getTid()).getUID();
                    String wid = DataEngine.wkMap.get(cache.getTid()).getWID();
                    cacheDataList.remove(cache);

                    MessageHandler msgHandler = new MessageHandler();
                    msgHandler.sendMessageToUser(uid, JSONObject.toJSONString(cacheDataList));
                    //记录入库
                    RecordEntity recordEntity = new RecordEntity();
                    recordEntity.setWID(wid);
                    recordEntity.setRCDATE("");
                    recordEntity.setRCTIME(sdf.format(current));
                    recordEntity.setTAGSTATE(1);
                    iRecordDao.AddRecord(recordEntity);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
