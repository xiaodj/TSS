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
}
