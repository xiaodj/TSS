package com.tssweb.entity;

import org.springframework.stereotype.Component;

/**
 * Created by xiaodj on 2018/11/28.
 */
@Component
public class LicenceEntity {
    private String  LID;    //许可证ID
    private String  LCNAME; //许可证名称
    private String  LCDATE; //许可证过期日期
    private Integer WID;    //员工ID
    private String  WKCHNAME; //员工中文姓名

    public String getLID() {
        return LID;
    }

    public void setLID(String LID) {
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

    public Integer getWID() {
        return WID;
    }

    public void setWID(Integer WID) {
        this.WID = WID;
    }

    public String getWKCHNAME() {
        return WKCHNAME;
    }

    public void setWKCHNAME(String WKCHNAME) {
        this.WKCHNAME = WKCHNAME;
    }
}
