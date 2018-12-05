package com.tssweb.dto;

public class LicencesInfo {
    private String lcname;  //许可证名称
    private String lcdate;  //许可证过期时间

    public String getLcname() {
        return lcname;
    }

    public void setLcname(String lcname) {
        this.lcname = lcname;
    }

    public String getLcdate() {
        return lcdate;
    }

    public void setLcdate(String lcdate) {
        this.lcdate = lcdate;
    }
}
