package com.tssweb.entity;

import org.springframework.stereotype.Component;

/**
 * Created by xiaodj on 2018/11/28.
 */
@Component
public class HardwareSetEntity {
    private String  GID;    //硬件网关ID
    private String  GWNAME; //硬件网关名称
    private String  FRSPEED;    //读卡器读卡频率
    private String  FRRANGE;    //读卡器读取距离
    private Integer FRSTATE;    //读卡器状态
    private Integer UID;        //用户编码

    public String getGID() {
        return GID;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }

    public String getGWNAME() {
        return GWNAME;
    }

    public void setGWNAME(String GWNAME) {
        this.GWNAME = GWNAME;
    }

    public String getFRSPEED() {
        return FRSPEED;
    }

    public void setFRSPEED(String FRSPEED) {
        this.FRSPEED = FRSPEED;
    }

    public String getFRRANGE() {
        return FRRANGE;
    }

    public void setFRRANGE(String FRRANGE) {
        this.FRRANGE = FRRANGE;
    }

    public Integer getFRSTATE() {
        return FRSTATE;
    }

    public void setFRSTATE(Integer FRSTATE) {
        this.FRSTATE = FRSTATE;
    }

    public Integer getUID() {
        return UID;
    }

    public void setUID(Integer UID) {
        this.UID = UID;
    }
}
