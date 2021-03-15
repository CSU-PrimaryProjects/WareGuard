package com.school.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.manager.exception.BaseDataException;
import com.school.manager.mapper.EmpMapper;
import com.school.manager.model.ResultType;
import com.school.manager.model.Emp;
import com.school.manager.service.EmpService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.util.List;


@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper EmpMapper;

    @Override
    public PageInfo<Emp> queryEmpList(Emp Emp, PageInfo pageInfo) {
        PageInfo<Emp> carPageInfo;
        try {
            Integer currentPage = pageInfo.getPageNum();
            Integer pageSize = pageInfo.getPageSize();
            Example example = new Example(Emp.class);
            Example.Criteria criteria = example.createCriteria();
            if (Emp != null && StringUtils.isNotBlank(Emp.getName())) {
                criteria.andEqualTo("name", Emp.getName());
            }
         //   criteria.andNotEqualTo("name","admin");
            PageHelper.startPage(currentPage, pageSize);
            List<Emp> Emps = EmpMapper.selectByExample(example);
            carPageInfo = new PageInfo<>(Emps);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "EmpService error at func(queryEmpList) " + e);
            throw e;
        }
        return carPageInfo;
    }

    @Override
    public void add(Emp Emp) {
        try {
            EmpMapper.insert(Emp);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update(Emp Emp) {
        try {
            EmpMapper.updateByPrimaryKeySelective(Emp);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            EmpMapper.deleteByPrimaryKey(id);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Emp queryByCode(String code) {
        Example example = new Example(Emp.class);
        Example.Criteria criteria = example.createCriteria();
        try {
            if (StringUtils.isNotBlank(code)) {
                criteria.andEqualTo("code",code);
                Emp Emp = EmpMapper.selectOneByExample(example);
                return Emp;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Emp queryEmpById(Integer id) {
        Emp Emp = EmpMapper.selectByPrimaryKey(id);
        return Emp;
    }

}
