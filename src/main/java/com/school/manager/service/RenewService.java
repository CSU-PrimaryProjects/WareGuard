package com.school.manager.service;

import com.github.pagehelper.PageInfo;
import com.school.manager.model.Renew;

import java.util.List;

public interface RenewService {
    PageInfo<Renew> queryList(Renew model, PageInfo pageInfo);
    PageInfo<Renew> queryListByName(Renew model, PageInfo pageInfo);
    void add(Renew model);
    void update(Renew model);
    void delete(Integer id);
    Renew queryById(Integer id);
    List<Renew> queryAll(String name);
}
