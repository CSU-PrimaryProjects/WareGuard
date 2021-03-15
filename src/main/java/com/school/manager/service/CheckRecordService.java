package com.school.manager.service;

import com.school.manager.model.CheckRecord;

import java.util.List;

public interface CheckRecordService {
    void add(CheckRecord model);
    void delete(Integer e_id);
    CheckRecord queryByEId(Integer e_id);

    int queryCheckRecordAllNum();
}
