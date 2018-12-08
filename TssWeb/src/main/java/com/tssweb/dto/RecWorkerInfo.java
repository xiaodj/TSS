package com.tssweb.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecWorkerInfo {
    private String wid;
    private String chname;
    private String surname;
    private String enname;
    private List<LicencesInfo> licences;
    private List<RecTimeInfo> times;

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getChname() {
        return chname;
    }

    public void setChname(String chname) {
        this.chname = chname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    public List<LicencesInfo> getLicences() {
        return licences;
    }

    public void setLicences(List<LicencesInfo> licences) {
        this.licences = licences;
    }

    public List<RecTimeInfo> getTimes() {
        return times;
    }

    public void setTimes(List<RecTimeInfo> times) {
        this.times = times;
    }
}
