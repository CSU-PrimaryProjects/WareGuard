package com.school.manager.controller;

import com.school.manager.model.Equipment;
import com.school.manager.service.RecordService;
import com.github.pagehelper.PageInfo;
import com.school.manager.common.PageDataDto;
import com.school.manager.model.JsonResult;
import com.school.manager.model.ResultType;
import com.school.manager.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecordController {

    @Autowired
    private RecordService RecordService;

    @RequestMapping("/queryRecordList")
    public JsonResult queryRecordList (@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                     Equipment equipment) {
        JsonResult<PageDataDto> result = new JsonResult<PageDataDto>(ResultType.SUCCESS);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        PageInfo<Equipment> RecordPageInfo = RecordService.queryRecordList(equipment,pageInfo);
        List<Equipment> list = RecordPageInfo.getList();
        PageDataDto pageDataDto = new PageDataDto(RecordPageInfo.getTotal(),
                list,
                RecordPageInfo.getPageNum(),
                RecordPageInfo.getPageSize(),
                RecordPageInfo.getPages());
        result.setData(pageDataDto);
        return result;
    }

    @RequestMapping("/addRecord")
    public JsonResult addRecord (@RequestBody Record Record) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        RecordService.addRecord(Record);
        return result;
    }

    @RequestMapping("/updateRecord")
    public JsonResult updateRecord (@RequestBody Record Record) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        RecordService.updateRecord(Record);
        return result;
    }

    @RequestMapping("/deleteRecord")
    public JsonResult deleteRecord (@RequestParam(value="id") Integer id) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        RecordService.deleteRecord(id);
        return result;
    }

    @RequestMapping("/queryRecordById")
    public JsonResult queryRecordById (@RequestParam(value="id") Integer id) {
        JsonResult<Record> result = new JsonResult<>(ResultType.SUCCESS);
        Record Record = RecordService.queryRecordById(id);
        result.setData(Record);
        return result;
    }

    @RequestMapping("/queryRecordAll")
    public JsonResult queryRecordAll () {
        JsonResult<List<Record>> result = new JsonResult<>(ResultType.SUCCESS);
        List<Record> list = RecordService.queryRecordAll();
        result.setData(list);
        return result;
    }
    @RequestMapping("/updateRecordStatus")
    public JsonResult updateRecordStatus (@RequestBody Record Record) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        RecordService.updateRecordStatus(Record);
        return result;
    }

    @RequestMapping("/updateRecordStatusByUser")
    public JsonResult updateRecordStatusByUser (@RequestBody Record Record) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        RecordService.updateRecordStatus(Record);
        return result;
    }
}
