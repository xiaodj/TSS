package com.tssweb.netty;

import com.alibaba.fastjson.JSONObject;
import com.tssweb.dao.IRecordDao;
import com.tssweb.dao.IWorkerDao;
import com.tssweb.dto.RealDataDto;
import com.tssweb.entity.*;
import com.tssweb.service.impl.RecordServiceImpl;
import com.tssweb.service.impl.WorkerServiceImpl;
import com.tssweb.utils.SpringContextHolder;
import com.tssweb.websocket.MessageHandler;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class DataEngine implements Runnable{
    static public Integer uid;
    static public final Map<String, CacheData> dataMap; //检测到的缓存数据
    static public final Queue<byte[]> msgQueue;             //保存从读卡器接收到的待处理消息
    static public final Queue<Boolean> cmdQueue;

    private IWorkerDao iWorkerDao = SpringContextHolder.getBean(IWorkerDao.class);
    private IRecordDao iRecordDao = SpringContextHolder.getBean(IRecordDao.class);

    static {
        dataMap = new HashMap<String, CacheData>();
        msgQueue = new LinkedList<byte[]>();
        cmdQueue = new LinkedList<Boolean>();
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
                //读取sn  暂时不读取
                iSeparator1 = iSeparator2;
                iFlag = 1;
                continue;
            }

            try {
                String tagtype = new String(bytes, iSeparator1 + 5, 2,"ASCII");
                String tagid4 = new String(bytes, iSeparator1 + 7, 2,"ASCII");
                String tagid3 = new String(bytes, iSeparator1 + 9, 2,"ASCII");
                String tagid2 = new String(bytes, iSeparator1 + 11, 2,"ASCII");
                String tagid1 = new String(bytes, iSeparator1 + 13, 2,"ASCII");
                String tagid = tagtype + tagid1 + tagid2 + tagid3 + tagid4;
                if (!(tagid instanceof String))
                    continue;
                Date date = new Date();
                synchronized (dataMap){
                    if(dataMap.containsKey(tagid)) {
                        CacheData cacheData = dataMap.get(tagid);
                        if (cacheData.getStartdate() != null && cacheData.getUpdate() != null){
                            cacheData.setUpdate(date);
                        }else{
                            cacheData.setStartdate(date);
                            cacheData.setUpdate(date);
                            RecordEntity recordEntity = new RecordEntity();
                            recordEntity.setWID(cacheData.getWid());
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
                        WorkersEntity workersEntity = iWorkerDao.GetWorkersByTid(tagid);
                        if (workersEntity == null)
                            continue;
                        uid = workersEntity.getUID();
                        cacheData.setWid(workersEntity.getWID());
                        cacheData.setUsername(workersEntity.getWKCHNAME());
                        cacheData.setLcList(workersEntity.getLicenceEntities());
                        cacheData.setStartdate(date);
                        cacheData.setUpdate(date);
                        dataMap.put(tagid, cacheData);
                        RecordEntity recordEntity = new RecordEntity();
                        recordEntity.setWID(workersEntity.getWID());
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                        recordEntity.setRCDATE(dateFormat.format(date));
                        recordEntity.setRCTIME(timeFormat.format(date));
                        recordEntity.setTAGSTATE(0);
                        iRecordDao.AddRecord(recordEntity);
                        //推送
                        bSendFlag = true;
                    }
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
                    if (licenceEntity.getLCNAME().equals("绿卡"))
                        realDataDto.setLc1(licenceEntity.getLCDATE());
                    else if (licenceEntity.getLCNAME().equals("密卡"))
                        realDataDto.setLc2(licenceEntity.getLCDATE());
                    else if (licenceEntity.getLCNAME().equals("CP"))
                        realDataDto.setLc3(licenceEntity.getLCDATE());
                }
                realDataDtoList.add(realDataDto);
            }
            //推送
            Collections.sort(realDataDtoList);
            MessageHandler messageHandler = new MessageHandler();
            messageHandler.sendMessageToUser(uid, JSONObject.toJSONString(realDataDtoList));
            bSendFlag = false;
        }
    }

    static public byte CheckSum(byte[] bytes, int len){
        byte bcheck = 0;
        for (Integer i = 0;i < len; i++){
            bcheck += bytes[i];
        }
        return (byte) (~bcheck + 1);
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
