package com.school.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.manager.common.UserUtils;
import com.school.manager.mapper.ApplyMapper;
import com.school.manager.model.Apply;
import com.school.manager.service.ApplyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private ApplyMapper ApplyMapper;

    @Override
    public PageInfo<Apply> queryApplyList(Apply model, PageInfo pageInfo) {
        PageInfo<Apply> ApplyPageInfo;
        try {
            Integer currentPage = pageInfo.getPageNum();
            Integer pageSize = pageInfo.getPageSize();
            Example example = new Example(Apply.class);
            Example.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(model.getName())) {
                criteria.andEqualTo("name",model.getName());
            }
            if (!"admin".equals(UserUtils.getUserName())) {
                criteria.andEqualTo("createperson",UserUtils.getUserName());
            }
            PageHelper.startPage(currentPage, pageSize);
            List<Apply> list = ApplyMapper.selectByExample(example);
            ApplyPageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "ApplyService error at func(queryApplyList) " + e);
            throw e;
        }
        return ApplyPageInfo;
    }

    @Override
    public void addApply(Apply model) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            String imgpath = (String)session.getAttribute("imgpath");
            if (StringUtils.isBlank(UserUtils.getUserName())) {
               // throw new BaseDataException(ResultType.NOT_LOGIN);
            }
       //     model.setStatus("underReview");
            model.setCreateperson(UserUtils.getUserName());
            model.setPath(imgpath);
            ApplyMapper.insert(model);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "ApplyService error at func(addApply) " + e);
            throw e;
        }
    }

    @Override
    public void updateApply(Apply model) {
        try {
            ApplyMapper.updateByPrimaryKeySelective(model);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "ApplyService error at func(updateApply) " + e);
            throw e;
        }
    }

    @Override
    public void deleteApply(Integer id) {
        try {
            ApplyMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "ApplyService error at func(deleteApply) " + e);
            throw e;
        }
    }

    @Override
    public Apply queryApplyById(Integer id) {
        Apply Apply;
        try {
            Apply = ApplyMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "ApplyService error at func(queryApplyById) " + e);
            throw e;
        }
        return Apply;
    }

    @Override
    public List<Apply> queryApplyAll(String name) {
        List<Apply> list;
        try {
            Example example = new Example(Apply.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("status","leisure");
            if (StringUtils.isNotBlank(name)) {
                criteria.andEqualTo("name",name);
            }
            list = ApplyMapper.selectByExample(example);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "ApplyService error at func(queryApplyAll) " + e);
            throw e;
        }
        return list;
    }
}
