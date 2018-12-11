package com.tssweb.dto;

import org.springframework.stereotype.Component;

@Component
public class LoginDto extends BaseDto {
    private Integer uid;
    private String nickname;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
