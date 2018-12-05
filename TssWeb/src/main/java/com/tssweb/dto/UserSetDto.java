package com.tssweb.dto;

public class UserSetDto extends BaseDto {
    private String OnStartTime;     //上班区间的起始时间
    private String OnEndTime;       //上班区间的结束时间
    private String OffStartTime;    //下班区间的起始时间
    private String OffEndTime;      //下班区间的结束时间

    public String getOnStartTime() {
        return OnStartTime;
    }

    public void setOnStartTime(String onStartTime) {
        OnStartTime = onStartTime;
    }

    public String getOnEndTime() {
        return OnEndTime;
    }

    public void setOnEndTime(String onEndTime) {
        OnEndTime = onEndTime;
    }

    public String getOffStartTime() {
        return OffStartTime;
    }

    public void setOffStartTime(String offStartTime) {
        OffStartTime = offStartTime;
    }

    public String getOffEndTime() {
        return OffEndTime;
    }

    public void setOffEndTime(String offEndTime) {
        OffEndTime = offEndTime;
    }
}
