package com.tssweb.dto;

import org.springframework.stereotype.Component;

@Component
public class RecTimeInfo {
    private String intime;
    private String outtime;

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }
}
