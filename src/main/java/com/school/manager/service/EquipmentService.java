package com.school.manager.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.school.manager.model.Equipment;

public interface EquipmentService {
    PageInfo<Equipment> queryEquipmentList(Equipment model, PageInfo pageInfo);
    PageInfo<Equipment> queryEquipmentList1(Equipment model, PageInfo pageInfo);
    void addEquipment(Equipment model);
    void updateEquipment(Equipment model);
    void deleteEquipment(Integer id);
    Equipment queryEquipmentById(Integer id);
    Equipment queryEquipmentByCode(String code);
    List<Equipment> queryEquipmentAll(String name);

    List<Equipment> queryByGroup();

    int queryEquipmentNum();
    int queryEquipmentAllNum();
}
