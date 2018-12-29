package com.tssweb.entity;

import java.util.List;

/**
 * Created by xiaodj on 2018/12/29.
 */
public class WorkersEntity {
    private String  WID;            //员工编码
    private String  WKCHNAME;       //员工中文姓名
    private String  WKSURNAME;      //员工英文姓
    private String  WKENNAME;       //员工英文名
    private String  WKCARD;         //员工工作证
    private String  WKCCRSID;       //员工CCRSID
    private String  WKOTHER;        //其它
    private String  WKIMAGEPATH;    //员工头像路径
    private Integer UID;            //用户ID

    private List<LicenceEntity> licenceEntities;    //许可证

    public String getWID() {
        return WID;
    }

    public void setWID(String WID) {
        this.WID = WID;
    }

    public String getWKCHNAME() {
        return WKCHNAME;
    }

    public void setWKCHNAME(String WKCHNAME) {
        this.WKCHNAME = WKCHNAME;
    }

    public String getWKSURNAME() {
        return WKSURNAME;
    }

    public void setWKSURNAME(String WKSURNAME) {
        this.WKSURNAME = WKSURNAME;
    }

    public String getWKENNAME() {
        return WKENNAME;
    }

    public void setWKENNAME(String WKENNAME) {
        this.WKENNAME = WKENNAME;
    }

    public String getWKCARD() {
        return WKCARD;
    }

    public void setWKCARD(String WKCARD) {
        this.WKCARD = WKCARD;
    }

    public String getWKCCRSID() {
        return WKCCRSID;
    }

    public void setWKCCRSID(String WKCCRSID) {
        this.WKCCRSID = WKCCRSID;
    }

    public String getWKOTHER() {
        return WKOTHER;
    }

    public void setWKOTHER(String WKOTHER) {
        this.WKOTHER = WKOTHER;
    }

    public String getWKIMAGEPATH() {
        return WKIMAGEPATH;
    }

    public void setWKIMAGEPATH(String WKIMAGEPATH) {
        this.WKIMAGEPATH = WKIMAGEPATH;
    }

    public Integer getUID() {
        return UID;
    }

    public void setUID(Integer UID) {
        this.UID = UID;
    }

    public List<LicenceEntity> getLicenceEntities() {
        return licenceEntities;
    }

    public void setLicenceEntities(List<LicenceEntity> licenceEntities) {
        this.licenceEntities = licenceEntities;
    }
}
