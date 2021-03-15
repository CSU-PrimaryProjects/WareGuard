package com.school.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.manager.common.UserUtils;
import com.school.manager.mapper.FineMapper;
import com.school.manager.model.Fine;
import com.school.manager.service.FineService;
import com.school.manager.service.FineService;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FineServiceImpl implements FineService {

    @Autowired
    private FineMapper FineMapper;

    @Autowired
    private RecordService recordService;

    @Override
    public PageInfo<Fine> queryList(Fine model, PageInfo pageInfo) {
        PageInfo<Fine> FinePageInfo;
        try {
            Integer currentPage = pageInfo.getPageNum();
            Integer pageSize = pageInfo.getPageSize();
            Example example = new Example(Fine.class);
            Example.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(model.getName())) {
                criteria.andLike("name","%"+model.getName()+"%");
            }
            PageHelper.startPage(currentPage, pageSize);
            List<Fine> list = FineMapper.selectByExample(example);
            FinePageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "FineService error at func(queryFineList) " + e);
            throw e;
        }
        return FinePageInfo;
    }

    @Override
    public PageInfo<Fine> queryListByName(Fine model, PageInfo pageInfo) {
        PageInfo<Fine> FinePageInfo;
        try {
            Integer currentPage = pageInfo.getPageNum();
            Integer pageSize = pageInfo.getPageSize();
            Example example = new Example(Fine.class);
            Example.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(model.getE_name())) {
                criteria.andEqualTo("e_name",model.getE_name());
            }
            criteria.andEqualTo("name",UserUtils.getUserName());
            PageHelper.startPage(currentPage, pageSize);
            List<Fine> list = FineMapper.selectByExample(example);
            FinePageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "FineService error at func(queryFineList) " + e);
            throw e;
        }
        return FinePageInfo;
    }

    @Override
    public void add(Fine model) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            model.setCreatetime(sdf.format(new Date()));
            FineMapper.insert(model);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "FineService error at func(addFine) " + e);
            throw e;
        }
    }

    @Override
    public void update(Fine model) {
        try {
            FineMapper.updateByPrimaryKeySelective(model);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "FineService error at func(updateFine) " + e);
            throw e;
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            FineMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "FineService error at func(deleteFine) " + e);
            throw e;
        }
    }

    @Override
    public Fine queryById(Integer id) {
        Fine Fine = null;
        try {
            Fine = FineMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "FineService error at func(queryFineById) " + e);
            throw e;
        }
        return Fine;
    }

    @Override
    public List<Fine> queryAll(String name) {
        List<Fine> list;
        try {
            Example example = new Example(Fine.class);
            Example.Criteria criteria = example.createCriteria();
         //   criteria.andEqualTo("status","leisure");
            if (StringUtils.isNotBlank(name)) {
                criteria.andEqualTo("name",name);
            }
            list = FineMapper.selectByExample(example);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "FineService error at func(queryFineAll) " + e);
            throw e;
        }
        return list;
    }


}
