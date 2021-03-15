package com.school.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.manager.common.UserUtils;
import com.school.manager.mapper.EquipmentMapper;
import com.school.manager.model.Record;
import com.school.manager.service.EquipmentService;
import com.school.manager.model.Equipment;
import com.school.manager.service.RecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentMapper EquipmentMapper;

    @Autowired
    private RecordService recordService;

    @Override
    public PageInfo<Equipment> queryEquipmentList(Equipment model, PageInfo pageInfo) {
        PageInfo<Equipment> EquipmentPageInfo;
        try {
            Integer currentPage = pageInfo.getPageNum();
            Integer pageSize = pageInfo.getPageSize();
            Example example = new Example(Equipment.class);
            Example.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(model.getName())) {
                criteria.andEqualTo("name",model.getName());
            }
            PageHelper.startPage(currentPage, pageSize);
            List<Equipment> list = EquipmentMapper.selectByExample(example);
            EquipmentPageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "EquipmentService error at func(queryEquipmentList) " + e);
            throw e;
        }
        return EquipmentPageInfo;
    }

    @Override
    public PageInfo<Equipment> queryEquipmentList1(Equipment model, PageInfo pageInfo) {
        PageInfo<Equipment> EquipmentPageInfo;
        try {
            Integer currentPage = pageInfo.getPageNum();
            Integer pageSize = pageInfo.getPageSize();
            Example example = new Example(Equipment.class);
            Example.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(model.getName())) {
                criteria.andEqualTo("name",model.getName());
            }
            criteria.andEqualTo("status",1);
            PageHelper.startPage(currentPage, pageSize);
            List<Equipment> list = EquipmentMapper.selectByExample(example);
            EquipmentPageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "EquipmentService error at func(queryEquipmentList) " + e);
            throw e;
        }
        return EquipmentPageInfo;
    }

    @Override
    public void addEquipment(Equipment model) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            String imgpath = (String)session.getAttribute("imgpath");
            if (StringUtils.isBlank(UserUtils.getUserName())) {
               // throw new BaseDataException(ResultType.NOT_LOGIN);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            model.setCreatetime(sdf.format(new Date()));
            model.setPath(imgpath);
            EquipmentMapper.insert(model);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "EquipmentService error at func(addEquipment) " + e);
            throw e;
        }
    }

    @Override
    public void updateEquipment(Equipment model) {
        try {
            EquipmentMapper.updateByPrimaryKeySelective(model);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "EquipmentService error at func(updateEquipment) " + e);
            throw e;
        }
    }

    @Override
    public void deleteEquipment(Integer id) {
        try {
            EquipmentMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "EquipmentService error at func(deleteEquipment) " + e);
            throw e;
        }
    }

    @Override
    public Equipment queryEquipmentById(Integer id) {
        Equipment Equipment = null;
        try {
            Equipment = EquipmentMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "EquipmentService error at func(queryEquipmentById) " + e);
            throw e;
        }
        return Equipment;
    }

    @Override
    public Equipment queryEquipmentByCode(String code) {
        Example example = new Example(Equipment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("code",code);
        Equipment equipment = EquipmentMapper.selectOneByExample(example);
        return equipment;
    }

    @Override
    public List<Equipment> queryEquipmentAll(String name) {
        List<Equipment> list;
        try {
            Example example = new Example(Equipment.class);
            Example.Criteria criteria = example.createCriteria();
         //   criteria.andEqualTo("status","leisure");
            if (StringUtils.isNotBlank(name)) {
                criteria.andEqualTo("name",name);
            }
            list = EquipmentMapper.selectByExample(example);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "EquipmentService error at func(queryEquipmentAll) " + e);
            throw e;
        }
        return list;
    }

    @Override
    public List<Equipment> queryByGroup() {
        return EquipmentMapper.queryByGroup();
    }

    @Override
    public int queryEquipmentNum() {
        Example example = new Example(Equipment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status",1);
        List<Equipment> equipment = EquipmentMapper.selectByExample(example);
        return equipment.size();
    }

    @Override
    public int queryEquipmentAllNum() {
        List<Equipment> equipment = EquipmentMapper.selectAll();
        return equipment.size();
    }

}
