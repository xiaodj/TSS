package com.tssweb.dto;

import java.util.List;

public class WorkerDto extends BaseDto {
    private String wid;     //员工编号
    private String surname; //英文姓
    private String enname;  //英文名
    private String chname;  //中文姓名
    private String wkcard;  //厂证
    private String ccrsid;  //CCRSID
    private String other;   //其它
    private List<LicencesInfo> licences;    //许可证信息
    private List<TagInfo> tags; //标签信息
    private String imagepath;   //头像路径

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

    public String getWkcard() {
        return wkcard;
    }

    public void setWkcard(String wkcard) {
        this.wkcard = wkcard;
    }

    public String getCcrsid() {
        return ccrsid;
    }

    public void setCcrsid(String ccrsid) {
        this.ccrsid = ccrsid;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public List<LicencesInfo> getLicences() {
        return licences;
    }

    public void setLicences(List<LicencesInfo> licences) {
        this.licences = licences;
    }

    public List<TagInfo> getTags() {
        return tags;
    }

    public void setTags(List<TagInfo> tags) {
        this.tags = tags;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }
}
