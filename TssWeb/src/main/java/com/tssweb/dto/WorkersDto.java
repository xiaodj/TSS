package com.tssweb.dto;

import java.util.List;

public class WorkersDto extends BaseDto{
    private List<WorkerBaseInfo> member;

    public List<WorkerBaseInfo> getMember() {
        return member;
    }

    public void setMember(List<WorkerBaseInfo> member) {
        this.member = member;
    }
}
