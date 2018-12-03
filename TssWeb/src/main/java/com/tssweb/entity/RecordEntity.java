package com.tssweb.entity;

import org.springframework.stereotype.Component;

/**
 * Created by xiaodj on 2018/11/28.
 */
@Component
public class RecordEntity {
    private Integer RID;    //记录ID
    private String  WID;    //员工编码
    private String  WKCHNAME;       //员工中文姓名
    private String  WKSURNAME;      //员工英文姓
    private String  WKENNAME;       //员工英文名
    private String  RCDATETIME; //记录时间
    private Integer TAGSTATE;   //标签状态  进来or离开

    public Integer getRID() {
        return RID;
    }

    public void setRID(Integer RID) {
        this.RID = RID;
    }

    public String getWID() {
        return WID;
    }

    public void setWID(String WID) {
        this.WID = WID;
    }

    public String getWKCHNAME() {
        return WKCHNAME;
    }

    public void setWKCHNAME(String WKCHNAME) {
        this.WKCHNAME = WKCHNAME;
    }

    public String getWKSURNAME() {
        return WKSURNAME;
    }

    public void setWKSURNAME(String WKSURNAME) {
        this.WKSURNAME = WKSURNAME;
    }

    public String getWKENNAME() {
        return WKENNAME;
    }

    public void setWKENNAME(String WKENNAME) {
        this.WKENNAME = WKENNAME;
    }

    public String getRCDATETIME() {
        return RCDATETIME;
    }

    public void setRCDATETIME(String RCDATETIME) {
        this.RCDATETIME = RCDATETIME;
    }

    public Integer getTAGSTATE() {
        return TAGSTATE;
    }

    public void setTAGSTATE(Integer TAGSTATE) {
        this.TAGSTATE = TAGSTATE;
    }
}
