package com.school.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.manager.common.UserUtils;
import com.school.manager.mapper.BorrowMapper;
import com.school.manager.model.Borrow;
import com.school.manager.model.Equipment;
import com.school.manager.service.BorrowService;
import com.school.manager.service.BorrowService;
import com.school.manager.service.EquipmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowMapper BorrowMapper;

    @Autowired
    private EquipmentService equipmentService;

    @Override
    public PageInfo<Equipment> queryList(Borrow model, PageInfo pageInfo) {
        PageInfo<Equipment> BorrowPageInfo;
        try {
            Integer currentPage = pageInfo.getPageNum();
            Integer pageSize = pageInfo.getPageSize();
            PageHelper.startPage(currentPage, pageSize);
            List<Equipment> equipment = BorrowMapper.queryPageList(UserUtils.getUserId());
            BorrowPageInfo = new PageInfo<>(equipment);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "BorrowService error at func(queryBorrowList) " + e);
            throw e;
        }
        return BorrowPageInfo;
    }

    @Override
    public PageInfo<Equipment> queryPageList(Borrow model, PageInfo pageInfo) {
        PageInfo<Equipment> BorrowPageInfo;
        List<Equipment> equipment;
        try {
            Integer currentPage = pageInfo.getPageNum();
            Integer pageSize = pageInfo.getPageSize();
            Example example = new Example(Borrow.class);
            Example.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(model.getName())) {
                equipment = BorrowMapper.queryPageList1(model.getName());
            }else {
                equipment = BorrowMapper.queryPageList2();
            }
            PageHelper.startPage(currentPage, pageSize);
            BorrowPageInfo = new PageInfo<>(equipment);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "BorrowService error at func(queryBorrowList) " + e);
            throw e;
        }
        return BorrowPageInfo;
    }

    @Override
    public void add(Borrow model) {
        try {
            BorrowMapper.insert(model);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "BorrowService error at func(addBorrow) " + e);
            throw e;
        }
    }

    @Override
    public void update(Borrow model) {
        try {
            BorrowMapper.updateByPrimaryKeySelective(model);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "BorrowService error at func(updateBorrow) " + e);
            throw e;
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            BorrowMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "BorrowService error at func(deleteBorrow) " + e);
            throw e;
        }
    }

    @Override
    public Borrow queryById(Integer id) {
        Borrow Borrow = null;
        try {
            Borrow = BorrowMapper.selectByPrimaryKey(id);
            if (Borrow != null) {
                Equipment equipment = equipmentService.queryEquipmentById(Borrow.getEquipment_id());
                Borrow.setName(equipment.getName());
            }
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "BorrowService error at func(queryBorrowById) " + e);
            throw e;
        }
        return Borrow;
    }

    @Override
    public Borrow queryByUserIdAndEid(Integer user_id, Integer eid) {
        try {
            Example example = new Example(Borrow.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("user_id",user_id);
            criteria.andEqualTo("equipment_id",eid);
            Borrow borrow = BorrowMapper.selectOneByExample(example);
            return borrow;
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "BorrowService error at func(queryBorrowById) " + e);
            throw e;
        }
    }

    @Override
    public List<Borrow> queryAll() {
        List<Borrow> list = null;
        try {
            list = BorrowMapper.selectAll();
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "BorrowService error at func(queryBorrowAll) " + e);
            throw e;
        }
        return list;
    }

    @Override
    public List<Borrow> queryBorrowGroupBy() {
        List<Borrow> borrows = BorrowMapper.queryBorrowGroupBy();
        return borrows;
    }
}
