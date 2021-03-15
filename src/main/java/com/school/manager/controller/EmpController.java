package com.school.manager.controller;

import com.github.pagehelper.PageInfo;
import com.school.manager.common.PageDataDto;
import com.school.manager.model.JsonResult;
import com.school.manager.model.ResultType;
import com.school.manager.model.Emp;
import com.school.manager.service.EmpService;
import com.school.manager.util.BaiduApiUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @Autowired
    private BaiduApiUtil baiduApiUtil;

    @RequestMapping("/queryEmpList")
    public JsonResult queryEmpList (@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                     Emp res) {
        JsonResult<PageDataDto> result = new JsonResult<PageDataDto>(ResultType.SUCCESS);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        PageInfo<Emp> carPageInfo = empService.queryEmpList(res,pageInfo);
        List<Emp> resList = carPageInfo.getList();
        PageDataDto pageDataDto = new PageDataDto(carPageInfo.getTotal(),
                resList,
                carPageInfo.getPageNum(),
                carPageInfo.getPageSize(),
                carPageInfo.getPages());
        result.setData(pageDataDto);
        return result;
    }

    @RequestMapping("/addEmp")
    public JsonResult addEmp (@RequestBody Emp Emp) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        Emp Emp1 = empService.queryByCode(Emp.getCode());
        if (Emp1 != null) {
            result.setCode(ResultType.EMP_EXIST.getCode());
            result.setMsg(ResultType.EMP_EXIST.getInfo());
            return result;
        }
        empService.add(Emp);
        return result;
    }
/*
    @RequestMapping("/faceRegister")
    public JsonResult faceRegister(@RequestBody Emp emp) {
        JsonResult<String> result = new JsonResult<>(ResultType.SUCCESS);
        Emp Emp1 = empService.queryByCode(emp.getCode());
        if (Emp1 != null) {
            result = new JsonResult<>(ResultType.NUMBER_REPEAT);
            return result;
        }
        org.json.JSONObject search = baiduApiUtil.searchBase64(emp.getImg());
        if (search.get("error_code").equals(0)) {
            result = new JsonResult<>(ResultType.STUDENT_EXIST);
            return result;
        }
        empService.add(emp);
        Emp emp2 = empService.queryByCode(emp.getCode());
        org.json.JSONObject register = baiduApiUtil.register(String.valueOf(emp2.getId()), emp.getImg(), BaiduApiUtil.IMG_TYPE_BASE64, String.valueOf(emp2.getId()), "user");
        if (register.get("error_code").equals(0)) {
            org.json.JSONObject registerResult = (org.json.JSONObject) register.get("result");
            emp2.setFace_token(registerResult.getString("face_token"));
            empService.update(emp2);
        } else {
            result.setMsg(register.get("error_msg").toString());
            result.setCode(3001);
            empService.delete(emp2.getId());
        }
        return result;
    }*/



    @RequestMapping("/deleteEmp")
    public JsonResult deleteResult (@RequestParam(value = "id") Integer id) {
        JsonResult<Emp> result = new JsonResult<Emp>(ResultType.SUCCESS);
        empService.delete(id);
        return result;
    }


    @RequestMapping("/queryEmpById")
    public JsonResult queryEmpById (@RequestParam(value = "id") Integer id) {
        JsonResult<Emp> result = new JsonResult<Emp>(ResultType.SUCCESS);
        Emp Emp = empService.queryEmpById(id);
        result.setData(Emp);
        return result;
    }


}
