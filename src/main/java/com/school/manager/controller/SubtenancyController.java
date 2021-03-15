package com.school.manager.controller;

import com.github.pagehelper.PageInfo;
import com.school.manager.common.PageDataDto;
import com.school.manager.model.Subtenancy;
import com.school.manager.model.JsonResult;
import com.school.manager.model.ResultType;
import com.school.manager.service.SubtenancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubtenancyController {

    @Autowired
    private SubtenancyService SubtenancyService;


    @RequestMapping("/querySubtenancyList")
    public JsonResult querySubtenancyList (@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                     Subtenancy Subtenancy) {
        JsonResult<PageDataDto> result = new JsonResult<PageDataDto>(ResultType.SUCCESS);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        PageInfo<Subtenancy> SubtenancyPageInfo = SubtenancyService.queryList(Subtenancy,pageInfo);
        List<Subtenancy> list = SubtenancyPageInfo.getList();
        PageDataDto pageDataDto = new PageDataDto(SubtenancyPageInfo.getTotal(),
                list,
                SubtenancyPageInfo.getPageNum(),
                SubtenancyPageInfo.getPageSize(),
                SubtenancyPageInfo.getPages());
        result.setData(pageDataDto);
        return result;
    }


    @RequestMapping("/queryByBorrowIn")
    public JsonResult queryByBorrowIn (@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                           @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                           Subtenancy Subtenancy) {
        JsonResult<PageDataDto> result = new JsonResult<PageDataDto>(ResultType.SUCCESS);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        PageInfo<Subtenancy> SubtenancyPageInfo = SubtenancyService.queryByBorrowIn(Subtenancy,pageInfo);
        List<Subtenancy> list = SubtenancyPageInfo.getList();
        PageDataDto pageDataDto = new PageDataDto(SubtenancyPageInfo.getTotal(),
                list,
                SubtenancyPageInfo.getPageNum(),
                SubtenancyPageInfo.getPageSize(),
                SubtenancyPageInfo.getPages());
        result.setData(pageDataDto);
        return result;
    }

    @RequestMapping("/queryByBorrowOut")
    public JsonResult queryByBorrowOut (@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                           @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                           Subtenancy Subtenancy) {
        JsonResult<PageDataDto> result = new JsonResult<PageDataDto>(ResultType.SUCCESS);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        PageInfo<Subtenancy> SubtenancyPageInfo = SubtenancyService.queryByBorrowOut(Subtenancy,pageInfo);
        List<Subtenancy> list = SubtenancyPageInfo.getList();
        PageDataDto pageDataDto = new PageDataDto(SubtenancyPageInfo.getTotal(),
                list,
                SubtenancyPageInfo.getPageNum(),
                SubtenancyPageInfo.getPageSize(),
                SubtenancyPageInfo.getPages());
        result.setData(pageDataDto);
        return result;
    }
    @RequestMapping("/addSubtenancy")
    public JsonResult addSubtenancy (@RequestBody Subtenancy Subtenancy) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        SubtenancyService.add(Subtenancy);
        return result;
    }

    @RequestMapping("/updateSubtenancy")
    public JsonResult updateSubtenancy (@RequestBody Subtenancy Subtenancy) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        SubtenancyService.update(Subtenancy);
        return result;
    }

    @RequestMapping("/deleteSubtenancy")
    public JsonResult deleteSubtenancy (@RequestParam(value="id") Integer id) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        SubtenancyService.delete(id);
        return result;
    }

    @RequestMapping("/querySubtenancyById")
    public JsonResult querySubtenancyById (@RequestParam(value="id") Integer id) {
        JsonResult<Subtenancy> result = new JsonResult<>(ResultType.SUCCESS);
        Subtenancy Subtenancy = SubtenancyService.queryById(id);
        result.setData(Subtenancy);
        return result;
    }

    @RequestMapping("/querySubtenancyAll")
    public JsonResult querySubtenancyAll (@RequestParam(value="name") String name) {
        JsonResult<List<Subtenancy>> result = new JsonResult<>(ResultType.SUCCESS);
        List<Subtenancy> list = SubtenancyService.queryAll(name);
        result.setData(list);
        return result;
    }


}
