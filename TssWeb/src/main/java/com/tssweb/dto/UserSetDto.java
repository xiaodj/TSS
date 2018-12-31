package com.tssweb.dto;

import org.springframework.stereotype.Component;

@Component
public class UserSetDto extends BaseDto {
    private String sensitivity;     //灵敏度值
    private String tagstatus;       //标签识别状态
    private String onstarttime;     //上班区间的起始时间
    private String onendtime;       //上班区间的结束时间
    private String offstarttime;    //下班区间的起始时间
    private String offendtime;      //下班区间的结束时间
    private Integer timeout;        //离开的条件

    public String getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(String sensitivity) {
        this.sensitivity = sensitivity;
    }

    public String getTagstatus() {
        return tagstatus;
    }

    public void setTagstatus(String tagstatus) {
        this.tagstatus = tagstatus;
    }

    public String getOnstarttime() {
        return onstarttime;
    }

    public void setOnstarttime(String onstarttime) {
        this.onstarttime = onstarttime;
    }

    public String getOnendtime() {
        return onendtime;
    }

    public void setOnendtime(String onendtime) {
        this.onendtime = onendtime;
    }

    public String getOffstarttime() {
        return offstarttime;
    }

    public void setOffstarttime(String offstarttime) {
        this.offstarttime = offstarttime;
    }

    public String getOffendtime() {
        return offendtime;
    }

    public void setOffendtime(String offendtime) {
        this.offendtime = offendtime;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
