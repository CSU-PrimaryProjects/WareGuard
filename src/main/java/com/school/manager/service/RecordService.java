package com.school.manager.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.school.manager.model.Equipment;
import com.school.manager.model.Record;

public interface RecordService {
    PageInfo<Equipment> queryRecordList(Equipment equipment, PageInfo pageInfo);
    void addRecord(Record model);
    void updateRecord(Record model);
    void deleteRecord(Integer id);
    Record queryRecordById(Integer id);
    List<Record> queryRecordAll();
    List<Record> queryRecordListByGroup();
    List<Record> queryRecordListByUserId(Integer userId);
    void updateRecordStatus(Record record);
    void updateRecordStatusByUser(Record record);
}
