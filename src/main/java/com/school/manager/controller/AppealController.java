package com.school.manager.controller;

import com.github.pagehelper.PageInfo;
import com.school.manager.common.PageDataDto;
import com.school.manager.model.Appeal;
import com.school.manager.model.JsonResult;
import com.school.manager.model.ResultType;
import com.school.manager.service.AppealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppealController {

    @Autowired
    private AppealService AppealService;


    @RequestMapping("/queryAppealList")
    public JsonResult queryAppealList (@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                     Appeal Appeal) {
        JsonResult<PageDataDto> result = new JsonResult<PageDataDto>(ResultType.SUCCESS);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        PageInfo<Appeal> AppealPageInfo = AppealService.queryList(Appeal,pageInfo);
        List<Appeal> list = AppealPageInfo.getList();
        PageDataDto pageDataDto = new PageDataDto(AppealPageInfo.getTotal(),
                list,
                AppealPageInfo.getPageNum(),
                AppealPageInfo.getPageSize(),
                AppealPageInfo.getPages());
        result.setData(pageDataDto);
        return result;
    }
    @RequestMapping("/queryAppealListByName")
    public JsonResult queryAppealListByName (@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                     Appeal Appeal) {
        JsonResult<PageDataDto> result = new JsonResult<PageDataDto>(ResultType.SUCCESS);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        PageInfo<Appeal> AppealPageInfo = AppealService.queryListByName(Appeal,pageInfo);
        List<Appeal> list = AppealPageInfo.getList();
        PageDataDto pageDataDto = new PageDataDto(AppealPageInfo.getTotal(),
                list,
                AppealPageInfo.getPageNum(),
                AppealPageInfo.getPageSize(),
                AppealPageInfo.getPages());
        result.setData(pageDataDto);
        return result;
    }

    @RequestMapping("/addAppeal")
    public JsonResult addAppeal (@RequestBody Appeal Appeal) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        AppealService.add(Appeal);
        return result;
    }

    @RequestMapping("/updateAppeal")
    public JsonResult updateAppeal (@RequestBody Appeal Appeal) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        AppealService.update(Appeal);
        return result;
    }

    @RequestMapping("/deleteAppeal")
    public JsonResult deleteAppeal (@RequestParam(value="id") Integer id) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        AppealService.delete(id);
        return result;
    }

    @RequestMapping("/queryAppealById")
    public JsonResult queryAppealById (@RequestParam(value="id") Integer id) {
        JsonResult<Appeal> result = new JsonResult<>(ResultType.SUCCESS);
        Appeal Appeal = AppealService.queryById(id);
        result.setData(Appeal);
        return result;
    }

    @RequestMapping("/queryAppealAll")
    public JsonResult queryAppealAll (@RequestParam(value="name") String name) {
        JsonResult<List<Appeal>> result = new JsonResult<>(ResultType.SUCCESS);
        List<Appeal> list = AppealService.queryAll(name);
        result.setData(list);
        return result;
    }


}
