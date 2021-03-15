package com.school.manager.controller;

import com.github.pagehelper.PageInfo;
import com.school.manager.common.PageDataDto;
import com.school.manager.model.Renew;
import com.school.manager.model.JsonResult;
import com.school.manager.model.ResultType;
import com.school.manager.service.RenewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RenewController {

    @Autowired
    private RenewService RenewService;


    @RequestMapping("/queryRenewList")
    public JsonResult queryRenewList (@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                     Renew Renew) {
        JsonResult<PageDataDto> result = new JsonResult<PageDataDto>(ResultType.SUCCESS);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        PageInfo<Renew> RenewPageInfo = RenewService.queryList(Renew,pageInfo);
        List<Renew> list = RenewPageInfo.getList();
        PageDataDto pageDataDto = new PageDataDto(RenewPageInfo.getTotal(),
                list,
                RenewPageInfo.getPageNum(),
                RenewPageInfo.getPageSize(),
                RenewPageInfo.getPages());
        result.setData(pageDataDto);
        return result;
    }
    @RequestMapping("/queryRenewListByName")
    public JsonResult queryRenewListByName (@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                     Renew Renew) {
        JsonResult<PageDataDto> result = new JsonResult<PageDataDto>(ResultType.SUCCESS);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        PageInfo<Renew> RenewPageInfo = RenewService.queryListByName(Renew,pageInfo);
        List<Renew> list = RenewPageInfo.getList();
        PageDataDto pageDataDto = new PageDataDto(RenewPageInfo.getTotal(),
                list,
                RenewPageInfo.getPageNum(),
                RenewPageInfo.getPageSize(),
                RenewPageInfo.getPages());
        result.setData(pageDataDto);
        return result;
    }

    @RequestMapping("/addRenew")
    public JsonResult addRenew (@RequestBody Renew Renew) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        RenewService.add(Renew);
        return result;
    }

    @RequestMapping("/updateRenew")
    public JsonResult updateRenew (@RequestBody Renew Renew) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        RenewService.update(Renew);
        return result;
    }

    @RequestMapping("/deleteRenew")
    public JsonResult deleteRenew (@RequestParam(value="id") Integer id) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        RenewService.delete(id);
        return result;
    }

    @RequestMapping("/queryRenewById")
    public JsonResult queryRenewById (@RequestParam(value="id") Integer id) {
        JsonResult<Renew> result = new JsonResult<>(ResultType.SUCCESS);
        Renew Renew = RenewService.queryById(id);
        result.setData(Renew);
        return result;
    }

    @RequestMapping("/queryRenewAll")
    public JsonResult queryRenewAll (@RequestParam(value="name") String name) {
        JsonResult<List<Renew>> result = new JsonResult<>(ResultType.SUCCESS);
        List<Renew> list = RenewService.queryAll(name);
        result.setData(list);
        return result;
    }


}
