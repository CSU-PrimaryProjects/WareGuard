package com.school.manager.service.impl;

import com.school.manager.common.UserUtils;
import com.school.manager.exception.BaseDataException;
import com.school.manager.mapper.RecordMapper;
import com.school.manager.model.Equipment;
import com.school.manager.model.ResultType;
import com.school.manager.model.User;
import com.school.manager.service.EquipmentService;
import com.school.manager.service.RecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.manager.model.Record;
import com.school.manager.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper RecordMapper;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private UserService userService;

    @Override
    public PageInfo<Equipment> queryRecordList(Equipment model, PageInfo pageInfo) {
        PageInfo<Equipment> RecordPageInfo;
        try {
            Integer currentPage = pageInfo.getPageNum();
            Integer pageSize = pageInfo.getPageSize();
            Example example = new Example(Record.class);
            Example.Criteria criteria = example.createCriteria();
            if (!"admin".equals(UserUtils.getUserName())) {
                criteria.andEqualTo("user_id",UserUtils.getUserId());
            }
            PageHelper.startPage(currentPage, pageSize);
            example.setOrderByClause("createtime desc");
            List<Record> list = RecordMapper.selectByExample(example);
            List<Equipment> equipmentList = new ArrayList<>();
            list.forEach(item -> {
                Equipment equipment = queryBookById(item.getBook_id());
                User user = userService.queryUserById(item.getUser_id());
                if (equipment != null && user != null) {
                 /*   equipment.setRecord_id(item.getId());
                    equipment.setStatus(item.getStatus());
                    equipment.setBorrower(user.getName());*/
                    equipment.setCreatetime(item.getCreatetime());
                    equipmentList.add(equipment);
                }
            });
            if (StringUtils.isNotBlank(model.getName())) {
                List<Equipment> equipment = equipmentList.stream().filter(s-> s.getName().equals(model.getName())).collect(Collectors.toList());
                RecordPageInfo = new PageInfo<>(equipment);
            }else {
                RecordPageInfo = new PageInfo<>(equipmentList);
            }

        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "RecordService error at func(queryRecordList) " + e);
            throw e;
        }
        return RecordPageInfo;
    }

    /**
     * 借出记录
     * @param model
     */
    @Override
    public void addRecord(Record model) {
        try {
            if (StringUtils.isBlank(UserUtils.getUserName())) {
                throw new BaseDataException(ResultType.NOT_LOGIN);
            }
            List<Record> records = queryRecordListByUserIdAndBookId(UserUtils.getUserId(), model.getBook_id());
            if (records != null && records.size() > 0) {
                throw new BaseDataException(ResultType.NO_MORE_THAN);
            }
            List<Record> records1 = queryRecordListByUserId(UserUtils.getUserId());
            if (records1 != null && records1.size() > 2) {
                throw new BaseDataException(ResultType.NO_MORE_THAN_THREE);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            model.setCreatetime(sdf.format(new Date()));
            model.setUser_id(UserUtils.getUserId());
            model.setStatus("audit");

            RecordMapper.insert(model);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "RecordService error at func(addRecord) " + e);
            throw e;
        }
    }

    private Equipment queryBookById (Integer id) {
        Equipment equipment = equipmentService.queryEquipmentById(id);
        return equipment;
    }
    @Override
    public void updateRecord(Record model) {
        try {
            RecordMapper.updateByPrimaryKeySelective(model);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "RecordService error at func(updateRecord) " + e);
            throw e;
        }
    }

    @Override
    public void deleteRecord(Integer id) {
        try {
            RecordMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "RecordService error at func(deleteRecord) " + e);
            throw e;
        }
    }

    @Override
    public Record queryRecordById(Integer id) {
        Record Record = null;
        try {
            Record = RecordMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "RecordService error at func(queryRecordById) " + e);
            throw e;
        }
        return Record;
    }

    @Override
    public List<Record> queryRecordAll() {
        List<Record> list = null;
        try {
            list = RecordMapper.selectAll();
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "RecordService error at func(queryRecordAll) " + e);
            throw e;
        }
        return list;
    }

    @Override
    public List<Record> queryRecordListByGroup() {
        List<Record> records = RecordMapper.queryRecordListByGroup();
        return records;
    }

    @Override
    public List<Record> queryRecordListByUserId(Integer userId) {
        Example example = new Example(Record.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("user_id",userId);
        List<Record> records = RecordMapper.selectByExample(example);
        return records;
    }

    @Override
    public void updateRecordStatus(Record record) {
        Record record1 = RecordMapper.selectByPrimaryKey(record.getId());
        if (record1 != null) {
            record1.setStatus(record.getStatus());
            RecordMapper.updateByPrimaryKeySelective(record1);
            //同意图书剩余数量减一
            Equipment equipment = equipmentService.queryEquipmentById(record1.getBook_id());
            /*if ("agree".equals(record.getStatus())) {
                equipment.setQuantity(equipment.getQuantity()-1);
                equipmentService.updateEquipment(equipment);
            }else if ("return".equals(record.getStatus())) {
                equipment.setQuantity(equipment.getQuantity()+1);
                equipmentService.updateEquipment(equipment);
            }*/
        }
    }
    @Override
    public void updateRecordStatusByUser(Record record) {
        Record record1 = RecordMapper.selectByPrimaryKey(record.getId());
        if (record1 != null) {
            record1.setStatus(record.getStatus());
            RecordMapper.updateByPrimaryKeySelective(record1);
        }
    }

    private List<Record> queryRecordListByUserIdAndBookId(Integer userId,Integer bookId) {
        Example example = new Example(Record.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("user_id",userId);
        criteria.andEqualTo("book_id",bookId);
        List<Record> records = RecordMapper.selectByExample(example);
        return records;
    }
}
