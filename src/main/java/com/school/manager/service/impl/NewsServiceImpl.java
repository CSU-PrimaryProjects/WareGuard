package com.school.manager.service.impl;

import com.school.manager.common.UserUtils;
import com.school.manager.mapper.NewsMapper;
import com.school.manager.service.NewsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.manager.model.News;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper NewsMapper;

    @Override
    public PageInfo<News> queryNewsList(News model, PageInfo pageInfo) {
        PageInfo<News> NewsPageInfo;
        try {
            Integer currentPage = pageInfo.getPageNum();
            Integer pageSize = pageInfo.getPageSize();
            Example example = new Example(News.class);
            Example.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(model.getTitle())) {
                criteria.andEqualTo("title",model.getTitle());
            }
            example.setOrderByClause("createtime desc");
            PageHelper.startPage(currentPage, pageSize);
            List<News> list = NewsMapper.selectByExample(example);
            NewsPageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "NewsService error at func(queryNewsList) " + e);
            throw e;
        }
        return NewsPageInfo;
    }

    @Override
    public void addNews(News model) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            model.setCreatetime(sdf.format(new Date()));
            model.setCreateperson(UserUtils.getUserName());
            NewsMapper.insert(model);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "NewsService error at func(addNews) " + e);
            throw e;
        }
    }

    @Override
    public void updateNews(News model) {
        try {
            NewsMapper.updateByPrimaryKeySelective(model);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "NewsService error at func(updateNews) " + e);
            throw e;
        }
    }

    @Override
    public void deleteNews(Integer id) {
        try {
            NewsMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "NewsService error at func(deleteNews) " + e);
            throw e;
        }
    }

    @Override
    public News queryNewsById(Integer id) {
        News News = null;
        try {
            News = NewsMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "NewsService error at func(queryNewsById) " + e);
            throw e;
        }
        return News;
    }

    @Override
    public List<News> queryNewsAll() {
        List<News> list = null;
        try {
            list = NewsMapper.selectAll();
        } catch (Exception e) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "NewsService error at func(queryNewsAll) " + e);
            throw e;
        }
        return list;
    }
}
