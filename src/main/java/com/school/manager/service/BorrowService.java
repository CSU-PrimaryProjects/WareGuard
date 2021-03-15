package com.school.manager.service;

import com.github.pagehelper.PageInfo;
import com.school.manager.model.Borrow;
import com.school.manager.model.Equipment;

import java.util.List;

public interface BorrowService {
    PageInfo<Equipment> queryList(Borrow model, PageInfo pageInfo);
    void add(Borrow model);
    void update(Borrow model);
    void delete(Integer id);
    Borrow queryById(Integer id);
    Borrow queryByUserIdAndEid(Integer user_id,Integer eid);
    List<Borrow> queryAll();
    List<Borrow> queryBorrowGroupBy();
    PageInfo<Equipment> queryPageList(Borrow model, PageInfo pageInfo);
}
