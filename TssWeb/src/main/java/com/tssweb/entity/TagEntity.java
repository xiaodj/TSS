package com.tssweb.entity;

import org.springframework.stereotype.Component;

/**
 * Created by xiaodj on 2018/11/28.
 */
@Component
public class TagEntity {
    private String TID;    //标签ID
    private String TAGNAME;    //标签名称
    private String WID;    //员工ID
    private String WKCHNAME;       //员工中文姓名
    private String WKSURNAME;      //员工英文姓
    private String WKENNAME;       //员工英文名

    public String getTID() {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    public String getTAGNAME() {
        return TAGNAME;
    }

    public void setTAGNAME(String TAGNAME) {
        this.TAGNAME = TAGNAME;
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
}
