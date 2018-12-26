package com.tssweb.netty;

import com.alibaba.fastjson.JSONObject;
import com.tssweb.dao.IRecordDao;
import com.tssweb.dao.IWorkerDao;
import com.tssweb.entity.LicenceEntity;
import com.tssweb.entity.RecordEntity;
import com.tssweb.entity.WorkerEntity;
import com.tssweb.websocket.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataEngine implements Runnable{
    static public final Map<String, List<LicenceEntity>> lcMap;  //保存读卡器读取的标签情况信息
    static public final Map<String, WorkerEntity> wkMap;         //用于缓存
    static public final List<CacheData> cacheDataList;   //保存实时显示的数据
    static public final Queue<byte[]> msgQueue;             //保存从读卡器接收到的待处理消息
    static {
        lcMap = new HashMap<String, List<LicenceEntity>>();
        wkMap = new HashMap<String, WorkerEntity>();
        cacheDataList = new ArrayList<CacheData>();
        msgQueue = new LinkedList<byte[]>();
    }

    @Autowired
    private IWorkerDao iWorkerDao;
    @Autowired
    private IRecordDao iRecordDao;

    public Integer getiOutTime() {
        return iOutTime;
    }

    public void setiOutTime(Integer iOutTime) {
        this.iOutTime = iOutTime;
    }

    //判别离开时的时间间隔
    private Integer iOutTime;

    //解析处理数据
    public void dataparas(byte[] bytes){
        Integer uid = 0;
        String wid = "";
        String tid = "";    //获取标签号
        String date = "";   //获取记录日期
        String time = "";   //获取记录时间
        String username = "";
        String lc1Date = "";
        String lc2Date = "";
        String lc3Date = "";

        //解析出标签编码与时间===============

        //获取uid
        if (wkMap.containsKey(tid)){
            uid = wkMap.get(tid).getUID();
            wid = wkMap.get(tid).getWID();
            username = wkMap.get(tid).getWKCHNAME();
        }else{
            //从数据库中获取
            WorkerEntity workerEntity = iWorkerDao.GetWorkerInfoByTID(tid);
            wkMap.put(tid, workerEntity);
            uid = workerEntity.getUID();
            wid = workerEntity.getWID();
            username = workerEntity.getWKCHNAME();
        }

        if (lcMap.containsKey(tid)){
            for (LicenceEntity licenceEntity:lcMap.get(tid)) {
                if (licenceEntity.getLCNAME().equals("绿卡到期日期"))
                    lc1Date = licenceEntity.getLCDATE();
                else if (licenceEntity.getLCNAME().equals("密卡到期日期"))
                    lc2Date = licenceEntity.getLCDATE();
                else if (licenceEntity.getLCNAME().equals("CP到期日期"))
                    lc3Date = licenceEntity.getLCDATE();
            }
        }else{
            //从数据库中获取
            List<LicenceEntity> licenceEntities = iWorkerDao.GetLicenceInfoByTID(tid);
            lcMap.put(tid, licenceEntities);

            for (LicenceEntity licenceEntity:licenceEntities) {
                if (licenceEntity.getLCNAME().equals("绿卡到期日期"))
                    lc1Date = licenceEntity.getLCDATE();
                else if (licenceEntity.getLCNAME().equals("密卡到期日期"))
                    lc2Date = licenceEntity.getLCDATE();
                else if (licenceEntity.getLCNAME().equals("CP到期日期"))
                    lc3Date = licenceEntity.getLCDATE();
            }
        }

        //新检测到
        boolean bIsExist = false;
        for (CacheData cache:cacheDataList) {
            if(cache.getUsername().equals(username)){
                cache.setTime(time);
                bIsExist = true;
                break;
            }
        }

        if (!bIsExist){
            CacheData cacheData = new CacheData();
            cacheData.setUsername(username);
            cacheData.setTime(time);
            cacheData.setLc1(lc1Date);
            cacheData.setLc2(lc2Date);
            cacheData.setLc3(lc3Date);
            cacheDataList.add(cacheData);

            //1.推送给前端
            MessageHandler msgHandler = new MessageHandler();
            msgHandler.sendMessageToUser(uid, JSONObject.toJSONString(cacheDataList));
            //2.记录入库
            RecordEntity recordEntity = new RecordEntity();
            recordEntity.setWID(wid);
            recordEntity.setRCDATE(date);
            recordEntity.setRCTIME(time);
            recordEntity.setTAGSTATE(0);
            iRecordDao.AddRecord(recordEntity);
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
                dataparas(bytes);
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
