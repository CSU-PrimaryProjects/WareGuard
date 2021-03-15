package com.school.manager.service;

import com.github.pagehelper.PageInfo;
import com.school.manager.model.Equipment;
import com.school.manager.model.Fine;

import java.util.List;

public interface FineService {
    PageInfo<Fine> queryList(Fine model, PageInfo pageInfo);
    PageInfo<Fine> queryListByName(Fine model, PageInfo pageInfo);
    void add(Fine model);
    void update(Fine model);
    void delete(Integer id);
    Fine queryById(Integer id);
    List<Fine> queryAll(String name);
}
