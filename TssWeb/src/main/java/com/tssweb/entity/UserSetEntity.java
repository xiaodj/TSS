package com.tssweb.entity;

import org.springframework.stereotype.Component;

/**
 * Created by xiaodj on 2018/11/28.
 */
@Component
public class UserSetEntity {
    private Integer SID;   //用户设置编号
    private String  ONSTARTTIME;    //上班时间段的开始时间
    private String  ONENDTIME;      //上班时间段的结束时间
    private String  OFFSTARTTIME;   //下班时间段的开始时间
    private String  OFFENDTIME;     //下班时间段的结束时间
    private Integer TIMEOUT;        //判断离开的条件
    private Integer UID;            //用户编码

    public Integer getSID() {
        return SID;
    }

    public void setSID(Integer SID) {
        this.SID = SID;
    }

    public String getONSTARTTIME() {
        return ONSTARTTIME;
    }

    public void setONSTARTTIME(String ONSTARTTIME) {
        this.ONSTARTTIME = ONSTARTTIME;
    }

    public String getONENDTIME() {
        return ONENDTIME;
    }

    public void setONENDTIME(String ONENDTIME) {
        this.ONENDTIME = ONENDTIME;
    }

    public String getOFFSTARTTIME() {
        return OFFSTARTTIME;
    }

    public void setOFFSTARTTIME(String OFFSTARTTIME) {
        this.OFFSTARTTIME = OFFSTARTTIME;
    }

    public String getOFFENDTIME() {
        return OFFENDTIME;
    }

    public void setOFFENDTIME(String OFFENDTIME) {
        this.OFFENDTIME = OFFENDTIME;
    }

    public Integer getTIMEOUT() {
        return TIMEOUT;
    }

    public void setTIMEOUT(Integer TIMEOUT) {
        this.TIMEOUT = TIMEOUT;
    }

    public Integer getUID() {
        return UID;
    }

    public void setUID(Integer UID) {
        this.UID = UID;
    }
}
