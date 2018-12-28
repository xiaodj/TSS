package com.tssweb.netty;

import com.tssweb.entity.LicenceEntity;

import java.util.List;

public class CacheData {
    private String tid;
    private String username;    //员工姓名
    private String time;
    private String lc1;
    private String lc2;
    private String lc3;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLc1() {
        return lc1;
    }

    public void setLc1(String lc1) {
        this.lc1 = lc1;
    }

    public String getLc2() {
        return lc2;
    }

    public void setLc2(String lc2) {
        this.lc2 = lc2;
    }

    public String getLc3() {
        return lc3;
    }

    public void setLc3(String lc3) {
        this.lc3 = lc3;
    }
}
