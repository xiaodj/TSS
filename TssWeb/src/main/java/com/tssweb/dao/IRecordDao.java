package com.tssweb.dao;

import com.tssweb.entity.RecordEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRecordDao {

    /**
     * 根据时间段查询所有员工记录
     */
    public List<RecordEntity> GetRecordsByDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 根据时间段以及员工编号查询员工记录
     */
    public List<RecordEntity> GetRecordsByDateAndWID(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("wid") String wid);
}
