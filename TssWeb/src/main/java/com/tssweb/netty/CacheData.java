package com.tssweb.netty;

import com.tssweb.entity.LicenceEntity;

import java.util.Date;
import java.util.List;

public class CacheData {
    private String username;    //员工姓名
    private Date   startdate;  //开始检测到的时间
    private Date   update;     //更新时间
    private List<LicenceEntity> lcList; //许可证

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public List<LicenceEntity> getLcList() {
        return lcList;
    }

    public void setLcList(List<LicenceEntity> lcList) {
        this.lcList = lcList;
    }
}
