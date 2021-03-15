package com.school.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.manager.common.UserUtils;
import com.school.manager.mapper.RenewMapper;
import com.school.manager.model.Renew;
import com.school.manager.service.RenewService;
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
public class RenewServiceImpl implements RenewService {

    @Autowired
    private RenewMapper RenewMapper;

    @Autowired
    private RecordService recordService;

    @Override
    public PageInfo<Renew> queryList(Renew model, PageInfo pageInfo) {
        PageInfo<Renew> RenewPageInfo;
        try {
            Integer currentPage = pageInfo.getPageNum();
            Integer pageSize = pageInfo.getPageSize();
            Example example = new Example(Renew.class);
            Example.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(model.getName())) {
                criteria.andEqualTo("name",model.getName());
            }
            PageHelper.startPage(currentPage, pageSize);
            List<Renew> list = RenewMapper.selectByExample(example);
            RenewPageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "RenewService error at func(queryRenewList) " + e);
            throw e;
        }
        return RenewPageInfo;
    }

    @Override
    public PageInfo<Renew> queryListByName(Renew model, PageInfo pageInfo) {
        PageInfo<Renew> RenewPageInfo;
        try {
            Integer currentPage = pageInfo.getPageNum();
            Integer pageSize = pageInfo.getPageSize();
            Example example = new Example(Renew.class);
            Example.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(model.getE_name())) {
                criteria.andEqualTo("e_name",model.getE_name());
            }
            criteria.andEqualTo("name",UserUtils.getUserName());
            PageHelper.startPage(currentPage, pageSize);
            List<Renew> list = RenewMapper.selectByExample(example);
            RenewPageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "RenewService error at func(queryRenewList) " + e);
            throw e;
        }
        return RenewPageInfo;
    }

    @Override
    public void add(Renew model) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            model.setCreatetime(sdf.format(new Date()));
            model.setName(UserUtils.getUserName());
            RenewMapper.insert(model);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "RenewService error at func(addRenew) " + e);
            throw e;
        }
    }

    @Override
    public void update(Renew model) {
        try {
            RenewMapper.updateByPrimaryKeySelective(model);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "RenewService error at func(updateRenew) " + e);
            throw e;
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            RenewMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "RenewService error at func(deleteRenew) " + e);
            throw e;
        }
    }

    @Override
    public Renew queryById(Integer id) {
        Renew Renew = null;
        try {
            Renew = RenewMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "RenewService error at func(queryRenewById) " + e);
            throw e;
        }
        return Renew;
    }

    @Override
    public List<Renew> queryAll(String name) {
        List<Renew> list;
        try {
            Example example = new Example(Renew.class);
            Example.Criteria criteria = example.createCriteria();
         //   criteria.andEqualTo("status","leisure");
            if (StringUtils.isNotBlank(name)) {
                criteria.andEqualTo("name",name);
            }
            list = RenewMapper.selectByExample(example);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "RenewService error at func(queryRenewAll) " + e);
            throw e;
        }
        return list;
    }


}
