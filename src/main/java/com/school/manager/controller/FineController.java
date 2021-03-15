package com.school.manager.controller;

import com.github.pagehelper.PageInfo;
import com.school.manager.common.PageDataDto;
import com.school.manager.model.Fine;
import com.school.manager.model.JsonResult;
import com.school.manager.model.ResultType;
import com.school.manager.service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FineController {

    @Autowired
    private FineService FineService;


    @RequestMapping("/queryFineList")
    public JsonResult queryFineList (@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                     Fine Fine) {
        JsonResult<PageDataDto> result = new JsonResult<PageDataDto>(ResultType.SUCCESS);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        PageInfo<Fine> FinePageInfo = FineService.queryList(Fine,pageInfo);
        List<Fine> list = FinePageInfo.getList();
        PageDataDto pageDataDto = new PageDataDto(FinePageInfo.getTotal(),
                list,
                FinePageInfo.getPageNum(),
                FinePageInfo.getPageSize(),
                FinePageInfo.getPages());
        result.setData(pageDataDto);
        return result;
    }
    @RequestMapping("/queryListByName")
    public JsonResult queryListByName (@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                     Fine Fine) {
        JsonResult<PageDataDto> result = new JsonResult<PageDataDto>(ResultType.SUCCESS);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        PageInfo<Fine> FinePageInfo = FineService.queryListByName(Fine,pageInfo);
        List<Fine> list = FinePageInfo.getList();
        PageDataDto pageDataDto = new PageDataDto(FinePageInfo.getTotal(),
                list,
                FinePageInfo.getPageNum(),
                FinePageInfo.getPageSize(),
                FinePageInfo.getPages());
        result.setData(pageDataDto);
        return result;
    }

    @RequestMapping("/addFine")
    public JsonResult addFine (@RequestBody Fine Fine) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        FineService.add(Fine);
        return result;
    }

    @RequestMapping("/updateFine")
    public JsonResult updateFine (@RequestBody Fine Fine) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        FineService.update(Fine);
        return result;
    }

    @RequestMapping("/deleteFine")
    public JsonResult deleteFine (@RequestParam(value="id") Integer id) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        FineService.delete(id);
        return result;
    }

    @RequestMapping("/queryFineById")
    public JsonResult queryFineById (@RequestParam(value="id") Integer id) {
        JsonResult<Fine> result = new JsonResult<>(ResultType.SUCCESS);
        Fine Fine = FineService.queryById(id);
        result.setData(Fine);
        return result;
    }

    @RequestMapping("/queryFineAll")
    public JsonResult queryFineAll (@RequestParam(value="name") String name) {
        JsonResult<List<Fine>> result = new JsonResult<>(ResultType.SUCCESS);
        List<Fine> list = FineService.queryAll(name);
        result.setData(list);
        return result;
    }


}
