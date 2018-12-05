package com.tssweb.dto;

public class HardwareSetDto extends BaseDto {
    private String frSpeed;     //读卡速度
    private String frRange;     //读卡距离
    private Integer frState;    //读卡状态

    public String getFrSpeed() {
        return frSpeed;
    }

    public void setFrSpeed(String frSpeed) {
        this.frSpeed = frSpeed;
    }

    public String getFrRange() {
        return frRange;
    }

    public void setFrRange(String frRange) {
        this.frRange = frRange;
    }

    public Integer getFrState() {
        return frState;
    }

    public void setFrState(Integer frState) {
        this.frState = frState;
    }
}
