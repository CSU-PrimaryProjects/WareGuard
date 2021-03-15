package com.school.manager.service;

import com.github.pagehelper.PageInfo;
import com.school.manager.model.Emp;

public interface EmpService {
    PageInfo<Emp> queryEmpList(Emp result, PageInfo pageInfo);
    void add(Emp Emp);
    void update(Emp Emp);
    void delete(Integer id);
    Emp queryByCode(String code);
    Emp queryEmpById(Integer id);
}
