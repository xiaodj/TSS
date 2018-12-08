package com.tssweb.dto;

import org.springframework.stereotype.Component;

@Component
public class LoginDto extends BaseDto {
    private Integer uid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
