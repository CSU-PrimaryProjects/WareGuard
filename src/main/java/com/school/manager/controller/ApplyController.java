package com.school.manager.controller;

import com.github.pagehelper.PageInfo;
import com.school.manager.common.PageDataDto;
import com.school.manager.model.Apply;
import com.school.manager.model.JsonResult;
import com.school.manager.model.ResultType;
import com.school.manager.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApplyController {

    @Autowired
    private ApplyService ApplyService;

    @RequestMapping("/queryApplyList")
    public JsonResult queryApplyList (@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                     Apply Apply) {
        JsonResult<PageDataDto> result = new JsonResult<PageDataDto>(ResultType.SUCCESS);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        PageInfo<Apply> ApplyPageInfo = ApplyService.queryApplyList(Apply,pageInfo);
        List<Apply> list = ApplyPageInfo.getList();
        PageDataDto pageDataDto = new PageDataDto(ApplyPageInfo.getTotal(),
                list,
                ApplyPageInfo.getPageNum(),
                ApplyPageInfo.getPageSize(),
                ApplyPageInfo.getPages());
        result.setData(pageDataDto);
        return result;
    }

    @RequestMapping("/addApply")
    public JsonResult addApply (@RequestBody Apply Apply) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        ApplyService.addApply(Apply);
        return result;
    }

    @RequestMapping("/updateApply")
    public JsonResult updateApply (@RequestBody Apply Apply) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        ApplyService.updateApply(Apply);
        return result;
    }

    @RequestMapping("/deleteApply")
    public JsonResult deleteApply (@RequestParam(value="id") Integer id) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        ApplyService.deleteApply(id);
        return result;
    }

    @RequestMapping("/queryApplyById")
    public JsonResult queryApplyById (@RequestParam(value="id") Integer id) {
        JsonResult<Apply> result = new JsonResult<>(ResultType.SUCCESS);
        Apply Apply = ApplyService.queryApplyById(id);
        result.setData(Apply);
        return result;
    }

    @RequestMapping("/queryApplyAll")
    public JsonResult queryApplyAll (@RequestParam(value="name") String name) {
        JsonResult<List<Apply>> result = new JsonResult<>(ResultType.SUCCESS);
        List<Apply> list = ApplyService.queryApplyAll(name);
        result.setData(list);
        return result;
    }

    @RequestMapping("/refuse")
    public JsonResult refuse (@RequestParam(value="id") Integer id) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        Apply apply = ApplyService.queryApplyById(id);
        if (apply != null) {
           // apply.setStatus("refuse");
        }
        ApplyService.updateApply(apply);
        return result;
    }
    @RequestMapping("/agree")
    public JsonResult agree (@RequestParam(value="id") Integer id) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        Apply apply = ApplyService.queryApplyById(id);
        if (apply != null) {
          //  apply.setStatus("agree");
        }
        ApplyService.updateApply(apply);
        return result;
    }
}
