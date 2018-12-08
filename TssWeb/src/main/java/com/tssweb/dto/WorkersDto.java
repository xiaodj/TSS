package com.tssweb.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkersDto extends BaseDto{
    private List<WorkerInfo> member;

    public List<WorkerInfo> getMember() {
        return member;
    }

    public void setMember(List<WorkerInfo> member) {
        this.member = member;
    }
}
