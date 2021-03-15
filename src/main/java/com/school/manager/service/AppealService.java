package com.school.manager.service;

import com.github.pagehelper.PageInfo;
import com.school.manager.model.Appeal;

import java.util.List;

public interface AppealService {
    PageInfo<Appeal> queryList(Appeal model, PageInfo pageInfo);
    PageInfo<Appeal> queryListByName(Appeal model, PageInfo pageInfo);
    void add(Appeal model);
    void update(Appeal model);
    void delete(Integer id);
    Appeal queryById(Integer id);
    List<Appeal> queryAll(String name);
}
