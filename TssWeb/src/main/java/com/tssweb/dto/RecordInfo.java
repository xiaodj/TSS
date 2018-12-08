package com.tssweb.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecordInfo {
    private String date;
    private List<RecWorkerInfo> workers;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<RecWorkerInfo> getWorkers() {
        return workers;
    }

    public void setWorkers(List<RecWorkerInfo> workers) {
        this.workers = workers;
    }
}
