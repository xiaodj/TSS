package com.tssweb.entity;

import org.springframework.stereotype.Component;

/**
 * Created by xiaodj on 2018/11/28.
 */
@Component
public class LicenceEntity {
    private Integer  LID;    //许可证ID
    private String  LCNAME; //许可证名称
    private String  LCDATE; //许可证过期日期
    private String  WID;    //员工编码

    public Integer getLID() {
        return LID;
    }

    public void setLID(Integer LID) {
        this.LID = LID;
    }

    public String getLCNAME() {
        return LCNAME;
    }

    public void setLCNAME(String LCNAME) {
        this.LCNAME = LCNAME;
    }

    public String getLCDATE() {
        return LCDATE;
    }

    public void setLCDATE(String LCDATE) {
        this.LCDATE = LCDATE;
    }

    public String getWID() {
        return WID;
    }

    public void setWID(String WID) {
        this.WID = WID;
    }
}
