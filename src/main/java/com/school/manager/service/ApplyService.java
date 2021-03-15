package com.school.manager.service;

import com.github.pagehelper.PageInfo;
import com.school.manager.model.Apply;

import java.util.List;

public interface ApplyService {
    PageInfo<Apply> queryApplyList(Apply model, PageInfo pageInfo);
    void addApply(Apply model);
    void updateApply(Apply model);
    void deleteApply(Integer id);
    Apply queryApplyById(Integer id);
    List<Apply> queryApplyAll(String name);
}
