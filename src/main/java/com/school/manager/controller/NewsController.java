package com.school.manager.controller;

import com.school.manager.service.NewsService;
import com.github.pagehelper.PageInfo;
import com.school.manager.common.PageDataDto;
import com.school.manager.model.JsonResult;
import com.school.manager.model.ResultType;
import com.school.manager.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewsController {

    @Autowired
    private NewsService NewsService;

    @RequestMapping("/queryNewsList")
    public JsonResult queryNewsList (@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                     News News) {
        JsonResult<PageDataDto> result = new JsonResult<PageDataDto>(ResultType.SUCCESS);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        PageInfo<News> NewsPageInfo = NewsService.queryNewsList(News,pageInfo);
        List<News> list = NewsPageInfo.getList();
        PageDataDto pageDataDto = new PageDataDto(NewsPageInfo.getTotal(),
                list,
                NewsPageInfo.getPageNum(),
                NewsPageInfo.getPageSize(),
                NewsPageInfo.getPages());
        result.setData(pageDataDto);
        return result;
    }

    @RequestMapping("/addNews")
    public JsonResult addNews (@RequestBody News News) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        NewsService.addNews(News);
        return result;
    }

    @RequestMapping("/updateNews")
    public JsonResult updateNews (@RequestBody News News) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        NewsService.updateNews(News);
        return result;
    }

    @RequestMapping("/deleteNews")
    public JsonResult deleteNews (@RequestParam(value="id") Integer id) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        NewsService.deleteNews(id);
        return result;
    }

    @RequestMapping("/queryNewsById")
    public JsonResult queryNewsById (@RequestParam(value="id") Integer id) {
        JsonResult<News> result = new JsonResult<>(ResultType.SUCCESS);
        News News = NewsService.queryNewsById(id);
        result.setData(News);
        return result;
    }

    @RequestMapping("/queryNewsAll")
    public JsonResult queryNewsAll () {
        JsonResult<List<News>> result = new JsonResult<>(ResultType.SUCCESS);
        List<News> list = NewsService.queryNewsAll();
        result.setData(list);
        return result;
    }

}
