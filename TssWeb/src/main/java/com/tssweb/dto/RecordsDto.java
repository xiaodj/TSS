package com.tssweb.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecordsDto extends BaseDto {
    private List<RecordInfo> records;

    public List<RecordInfo> getRecords() {
        return records;
    }

    public void setRecords(List<RecordInfo> records) {
        this.records = records;
    }
}
