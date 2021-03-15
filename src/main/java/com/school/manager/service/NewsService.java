package com.school.manager.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.school.manager.model.News;

public interface NewsService {
    PageInfo<News> queryNewsList(News model, PageInfo pageInfo);
    void addNews(News model);
    void updateNews(News model);
    void deleteNews(Integer id);
    News queryNewsById(Integer id);
    List<News> queryNewsAll();
}
