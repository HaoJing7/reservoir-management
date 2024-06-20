package com.back.reservoirmanagement.service;

import com.back.reservoirmanagement.pojo.entity.Record;
import com.back.reservoirmanagement.pojo.vo.DashBoardDataVO;

import java.util.List;

public interface DashBoardService {

    DashBoardDataVO getData();

    List<Record> getRecord();

    void deleteRecordById(Integer id);

    void addRecord(Record record);
}
