package com.tssweb.dto;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class RecordInfo {
    private String date;
    private String wid;
    private String chname;
    private String surname;
    private String enname;
    private String intime;
    private String outtime;
    private String lc1;
    private String lc2;
    private String lc3;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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
