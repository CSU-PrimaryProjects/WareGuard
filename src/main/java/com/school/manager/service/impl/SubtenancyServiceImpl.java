package com.school.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.manager.common.UserUtils;
import com.school.manager.exception.BaseDataException;
import com.school.manager.mapper.SubtenancyMapper;
import com.school.manager.model.*;
import com.school.manager.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SubtenancyServiceImpl implements SubtenancyService {

    @Autowired
    private SubtenancyMapper SubtenancyMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private BorrowService borrowService;

    @Override
    public PageInfo<Subtenancy> queryList(Subtenancy model, PageInfo pageInfo) {
        PageInfo<Subtenancy> SubtenancyPageInfo;
        try {
            Integer currentPage = pageInfo.getPageNum();
            Integer pageSize = pageInfo.getPageSize();
            Example example = new Example(Subtenancy.class);
            Example.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(model.getCode())) {
                criteria.andEqualTo("code",model.getCode());
            }
            PageHelper.startPage(currentPage, pageSize);
            List<Subtenancy> list = SubtenancyMapper.selectByExample(example);
            SubtenancyPageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "SubtenancyService error at func(querySubtenancyList) " + e);
            throw e;
        }
        return SubtenancyPageInfo;
    }

    @Override
    public PageInfo<Subtenancy> queryListByName(Subtenancy model, PageInfo pageInfo) {
        PageInfo<Subtenancy> SubtenancyPageInfo;
        try {
            Integer currentPage = pageInfo.getPageNum();
            Integer pageSize = pageInfo.getPageSize();
            Example example = new Example(Subtenancy.class);
            Example.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(model.getCode())) {
                criteria.andEqualTo("code",model.getCode());
            }
            criteria.andEqualTo("name",UserUtils.getUserName());
            PageHelper.startPage(currentPage, pageSize);
            List<Subtenancy> list = SubtenancyMapper.selectByExample(example);
            SubtenancyPageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "SubtenancyService error at func(querySubtenancyList) " + e);
            throw e;
        }
        return SubtenancyPageInfo;
    }

    @Override
    public PageInfo<Subtenancy> queryByBorrowIn(Subtenancy model, PageInfo pageInfo) {
        PageInfo<Subtenancy> SubtenancyPageInfo;
        try {
            Integer currentPage = pageInfo.getPageNum();
            Integer pageSize = pageInfo.getPageSize();
            Example example = new Example(Subtenancy.class);
            Example.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(model.getCode())) {
                criteria.andEqualTo("code",model.getCode());
            }
            criteria.andEqualTo("borrow_in",UserUtils.getUserName());
            PageHelper.startPage(currentPage, pageSize);
            List<Subtenancy> list = SubtenancyMapper.selectByExample(example);
            SubtenancyPageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "SubtenancyService error at func(querySubtenancyList) " + e);
            throw e;
        }
        return SubtenancyPageInfo;
    }

    @Override
    public PageInfo<Subtenancy> queryByBorrowOut(Subtenancy model, PageInfo pageInfo) {
        PageInfo<Subtenancy> SubtenancyPageInfo;
        try {
            Integer currentPage = pageInfo.getPageNum();
            Integer pageSize = pageInfo.getPageSize();
            Example example = new Example(Subtenancy.class);
            Example.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(model.getCode())) {
                criteria.andEqualTo("code",model.getCode());
            }
            criteria.andEqualTo("borrow_out",UserUtils.getUserName());
            PageHelper.startPage(currentPage, pageSize);
            List<Subtenancy> list = SubtenancyMapper.selectByExample(example);
            for (Subtenancy subtenancy : list) {
                Equipment equipment = equipmentService.queryEquipmentByCode(subtenancy.getCode());
                subtenancy.setLevel(equipment.getLevel());
            }
            SubtenancyPageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "SubtenancyService error at func(querySubtenancyList) " + e);
            throw e;
        }
        return SubtenancyPageInfo;
    }
    @Override
    public void add(Subtenancy model) {
        try {
           /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            model.setCreatetime(sdf.format(new Date()));*/
            User user = userService.queryByName(model.getBorrow_out());
            if (user == null) {
                throw new BaseDataException(ResultType.BORROW_IN_NOT_EXIST);
            }
            Equipment equipment = equipmentService.queryEquipmentByCode(model.getCode());
            if (equipment == null) {
                throw new BaseDataException(ResultType.EQUPMENT_NOT_EXIST);
            }
            Borrow borrow = borrowService.queryByUserIdAndEid(user.getId(), equipment.getId());
            if (borrow == null) {
                throw new BaseDataException(ResultType.BORROW_IN_NOT_HAVE);
            }
            model.setBorrow_in(UserUtils.getUserName());
            model.setE_id(equipment.getId());
            SubtenancyMapper.insert(model);
            borrow.setE_status("已转借");
            borrowService.update(borrow);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "SubtenancyService error at func(addSubtenancy) " + e);
            throw e;
        }
    }

    @Override
    public void addImport(Subtenancy model) {
        try {
            User user = userService.queryByName(model.getBorrow_out());
            User user1 = userService.queryByName(model.getSuperiors());
            if (user1 == null) {
                throw new BaseDataException(ResultType.LEADER_NOT_EXIST);
            }else if (user1 != null && user1.getRole().equals("普通员工")) {
                throw new BaseDataException(ResultType.LEADER_NOT_EXIST1);
            }
            if (user == null) {
                throw new BaseDataException(ResultType.BORROW_IN_NOT_EXIST);
            }
            Equipment equipment = equipmentService.queryEquipmentByCode(model.getCode());
            if (equipment == null) {
                throw new BaseDataException(ResultType.EQUPMENT_NOT_EXIST);
            }
            Borrow borrow = borrowService.queryByUserIdAndEid(user.getId(), equipment.getId());
            if (borrow == null) {
                throw new BaseDataException(ResultType.BORROW_IN_NOT_HAVE);
            }
            model.setBorrow_in(UserUtils.getUserName());
            model.setE_id(equipment.getId());
            SubtenancyMapper.insert(model);

            model.setBorrow_in(model.getSuperiors());
            SubtenancyMapper.insert(model);
            borrow.setE_status("已转借");
            borrowService.update(borrow);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "SubtenancyService error at func(addSubtenancy) " + e);
            throw e;
        }
    }

    @Override
    public void update(Subtenancy model) {
        try {
            SubtenancyMapper.updateByPrimaryKeySelective(model);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "SubtenancyService error at func(updateSubtenancy) " + e);
            throw e;
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            SubtenancyMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "SubtenancyService error at func(deleteSubtenancy) " + e);
            throw e;
        }
    }

    @Override
    public Subtenancy queryById(Integer id) {
        Subtenancy Subtenancy = null;
        try {
            Subtenancy = SubtenancyMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "SubtenancyService error at func(querySubtenancyById) " + e);
            throw e;
        }
        return Subtenancy;
    }

    @Override
    public List<Subtenancy> queryAll(String name) {
        List<Subtenancy> list;
        try {
            Example example = new Example(Subtenancy.class);
            Example.Criteria criteria = example.createCriteria();
         //   criteria.andEqualTo("status","leisure");
            if (StringUtils.isNotBlank(name)) {
                criteria.andEqualTo("name",name);
            }
            list = SubtenancyMapper.selectByExample(example);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "SubtenancyService error at func(querySubtenancyAll) " + e);
            throw e;
        }
        return list;
    }


}
