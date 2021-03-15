package com.school.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.manager.common.UserUtils;
import com.school.manager.mapper.AppealMapper;
import com.school.manager.model.Appeal;
import com.school.manager.service.AppealService;
import com.school.manager.service.AppealService;
import com.school.manager.service.RecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AppealServiceImpl implements AppealService {

    @Autowired
    private AppealMapper AppealMapper;

    @Autowired
    private RecordService recordService;

    @Override
    public PageInfo<Appeal> queryList(Appeal model, PageInfo pageInfo) {
        PageInfo<Appeal> AppealPageInfo;
        try {
            Integer currentPage = pageInfo.getPageNum();
            Integer pageSize = pageInfo.getPageSize();
            Example example = new Example(Appeal.class);
            Example.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(model.getName())) {
                criteria.andEqualTo("name",model.getName());
            }
            PageHelper.startPage(currentPage, pageSize);
            List<Appeal> list = AppealMapper.selectByExample(example);
            AppealPageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "AppealService error at func(queryAppealList) " + e);
            throw e;
        }
        return AppealPageInfo;
    }

    @Override
    public PageInfo<Appeal> queryListByName(Appeal model, PageInfo pageInfo) {
        PageInfo<Appeal> AppealPageInfo;
        try {
            Integer currentPage = pageInfo.getPageNum();
            Integer pageSize = pageInfo.getPageSize();
            Example example = new Example(Appeal.class);
            Example.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(model.getReason())) {
                criteria.andEqualTo("reason",model.getReason());
            }
            criteria.andEqualTo("name",UserUtils.getUserName());
            PageHelper.startPage(currentPage, pageSize);
            List<Appeal> list = AppealMapper.selectByExample(example);
            AppealPageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "AppealService error at func(queryAppealList) " + e);
            throw e;
        }
        return AppealPageInfo;
    }

    @Override
    public void add(Appeal model) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            model.setCreatetime(sdf.format(new Date()));
            AppealMapper.insert(model);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "AppealService error at func(addAppeal) " + e);
            throw e;
        }
    }

    @Override
    public void update(Appeal model) {
        try {
            AppealMapper.updateByPrimaryKeySelective(model);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "AppealService error at func(updateAppeal) " + e);
            throw e;
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            AppealMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "AppealService error at func(deleteAppeal) " + e);
            throw e;
        }
    }

    @Override
    public Appeal queryById(Integer id) {
        Appeal Appeal = null;
        try {
            Appeal = AppealMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "AppealService error at func(queryAppealById) " + e);
            throw e;
        }
        return Appeal;
    }

    @Override
    public List<Appeal> queryAll(String name) {
        List<Appeal> list;
        try {
            Example example = new Example(Appeal.class);
            Example.Criteria criteria = example.createCriteria();
         //   criteria.andEqualTo("status","leisure");
            if (StringUtils.isNotBlank(name)) {
                criteria.andEqualTo("name",name);
            }
            list = AppealMapper.selectByExample(example);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "AppealService error at func(queryAppealAll) " + e);
            throw e;
        }
        return list;
    }


}
