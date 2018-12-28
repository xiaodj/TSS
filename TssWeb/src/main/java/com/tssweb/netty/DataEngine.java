package com.tssweb.netty;

import com.alibaba.fastjson.JSONObject;
import com.tssweb.dao.IRecordDao;
import com.tssweb.dao.IWorkerDao;
import com.tssweb.dto.RealDataDto;
import com.tssweb.entity.LicenceEntity;
import com.tssweb.entity.RecordEntity;
import com.tssweb.entity.WorkerEntity;
import com.tssweb.websocket.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class DataEngine implements Runnable{
    static public Integer uid;
    static public final Map<String, CacheData> dataMap; //检测到的缓存数据
    static public final Queue<byte[]> msgQueue;             //保存从读卡器接收到的待处理消息

    @Autowired
    private IWorkerDao iWorkerDao;
    @Autowired
    private IRecordDao iRecordDao;

    static {
        dataMap = new HashMap<String, CacheData>();
        msgQueue = new LinkedList<byte[]>();
    }

    //解析处理数据
    public void RecordHandle(byte[] bytes){
        if (bytes[0] != 0x02 || bytes[1] != 0x2C)   //非自动上传数据
            return;

        Boolean bSendFlag = false;
        int iFlag = 0;  //0 获取SN, 1 获取RFID Data
        int iSeparator1 = 1;    //保存分割符处
        int iSeparator2 = 1;    //保存分割符处
        for (int i = 2; i < bytes.length; i++)
        {
            if(bytes[i] == 0x2C)
                iSeparator2 = i;

            if (iSeparator1 == iSeparator2)
                continue;

            if (iFlag == 0) {
                iFlag = 1;
                continue;
            }

            try {
                String tagid = new String(bytes, iSeparator1, iSeparator2-1-iSeparator1, "ASCII");
                Date date = new Date();
                if(dataMap.containsKey(tagid)) {
                    CacheData cacheData = dataMap.get(tagid);
                    if (cacheData.getStartdate() != null && cacheData.getUpdate() != null){
                        cacheData.setUpdate(date);
                    }else{
                        cacheData.setStartdate(date);
                        cacheData.setUpdate(date);
                        RecordEntity recordEntity = new RecordEntity();
                        recordEntity.setWID(tagid);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                        recordEntity.setRCDATE(dateFormat.format(date));
                        recordEntity.setRCTIME(timeFormat.format(date));
                        recordEntity.setTAGSTATE(0);
                        iRecordDao.AddRecord(recordEntity);
                        //推送
                        bSendFlag = true;
                    }
                }else{
                    CacheData cacheData = new CacheData();
                    //通过tagid 获取员工信息
                    WorkerEntity workerEntity = iWorkerDao.GetWorkerInfoByTID(tagid);
                    //通过wid 获取许可证信息
                    List<LicenceEntity> licenceEntities = iWorkerDao.GetLicenceInfoByWID(workerEntity.getWID());
                    uid = workerEntity.getUID();
                    cacheData.setUsername(workerEntity.getWKCHNAME());
                    cacheData.setLcList(licenceEntities);
                    cacheData.setStartdate(date);
                    cacheData.setUpdate(date);
                    dataMap.put(tagid, cacheData);
                    RecordEntity recordEntity = new RecordEntity();
                    recordEntity.setWID(tagid);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                    recordEntity.setRCDATE(dateFormat.format(date));
                    recordEntity.setRCTIME(timeFormat.format(date));
                    recordEntity.setTAGSTATE(0);
                    iRecordDao.AddRecord(recordEntity);
                    //推送
                    bSendFlag = true;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            iFlag = 0;
            iSeparator1 = iSeparator2;
        }

        //推送
        if (bSendFlag) {
            List<RealDataDto> realDataDtoList = new ArrayList<RealDataDto>();
            Iterator iter = dataMap.entrySet().iterator();
            while (iter.hasNext()){
                Map.Entry entry = (Map.Entry)iter.next();
                String tagid = (String) entry.getKey();
                CacheData cacheData = (CacheData) entry.getValue();
                if (cacheData.getStartdate() == null)
                    continue;

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
            //推送
            MessageHandler messageHandler = new MessageHandler();
            messageHandler.sendMessageToUser(uid, JSONObject.toJSONString(realDataDtoList));
        }
    }

    @Override
    public void run() {
        //获取时间间隔
        while (true)
        {
            if (msgQueue.size() > 0)
            {
                //要加线程同步
                byte[] bytes= msgQueue.poll();
                RecordHandle(bytes);
            }
            else
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
