package com.tssweb.dto;

import org.springframework.stereotype.Component;

@Component
public class WorkerInfo {
    private String wid;         //员工编号
    private String surname;     //英文姓
    private String enname;      //英文名
    private String chname;      //中文姓名

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
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

    public String getChname() {
        return chname;
    }

    public void setChname(String chname) {
        this.chname = chname;
    }

}
