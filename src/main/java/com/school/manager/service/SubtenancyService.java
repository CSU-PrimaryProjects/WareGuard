package com.school.manager.service;

import com.github.pagehelper.PageInfo;
import com.school.manager.model.Subtenancy;

import java.util.List;

public interface SubtenancyService {
    PageInfo<Subtenancy> queryList(Subtenancy model, PageInfo pageInfo);
    PageInfo<Subtenancy> queryListByName(Subtenancy model, PageInfo pageInfo);

    PageInfo<Subtenancy> queryByBorrowIn(Subtenancy model, PageInfo pageInfo);

    PageInfo<Subtenancy> queryByBorrowOut(Subtenancy model, PageInfo pageInfo);
    void add(Subtenancy model);
    void addImport(Subtenancy model);
    void update(Subtenancy model);
    void delete(Integer id);
    Subtenancy queryById(Integer id);
    List<Subtenancy> queryAll(String name);

}
