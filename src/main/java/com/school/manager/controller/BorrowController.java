package com.school.manager.controller;

import com.github.pagehelper.PageInfo;
import com.school.manager.common.PageDataDto;
import com.school.manager.model.*;
import com.school.manager.service.BorrowService;
import com.school.manager.service.CheckRecordService;
import com.school.manager.service.EquipmentService;
import com.school.manager.service.UserService;
import com.school.manager.util.BaiduApiUtil;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Relation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class BorrowController {

    @Autowired
    private BorrowService BorrowService;

    @Autowired
    private BaiduApiUtil baiduApiUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private CheckRecordService checkRecordService;



    @RequestMapping("/queryBorrowList")
    public JsonResult queryBorrowList (@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                     Borrow Borrow) {
        JsonResult<PageDataDto> result = new JsonResult<PageDataDto>(ResultType.SUCCESS);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        PageInfo<Equipment> equipmentPageInfo = BorrowService.queryList(Borrow, pageInfo);
        List<Equipment> list = equipmentPageInfo.getList();
        PageDataDto pageDataDto = new PageDataDto(equipmentPageInfo.getTotal(),
                list,
                equipmentPageInfo.getPageNum(),
                equipmentPageInfo.getPageSize(),
                equipmentPageInfo.getPages());
        result.setData(pageDataDto);
        return result;
    }

    @RequestMapping("/queryBorrowPageList")
    public JsonResult queryPageList (@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                       Borrow Borrow) {
        JsonResult<PageDataDto> result = new JsonResult<PageDataDto>(ResultType.SUCCESS);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        PageInfo<Equipment> equipmentPageInfo = BorrowService.queryPageList(Borrow, pageInfo);
        List<Equipment> list = equipmentPageInfo.getList();
        PageDataDto pageDataDto = new PageDataDto(equipmentPageInfo.getTotal(),
                list,
                equipmentPageInfo.getPageNum(),
                equipmentPageInfo.getPageSize(),
                equipmentPageInfo.getPages());
        result.setData(pageDataDto);
        return result;
    }

    @RequestMapping("/addBorrow")
    public JsonResult addBorrow (@RequestBody Borrow Borrow) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        BorrowService.add(Borrow);
        return result;
    }

    @PostMapping("/faceBorrow")
    public JsonResult faceSign(@RequestParam String img,@RequestParam Integer equipment_id,@RequestParam String borrow_date) throws ParseException {
        JsonResult<String> result = new JsonResult<>(ResultType.SUCCESS);

        org.json.JSONObject search = baiduApiUtil.searchBase64(img);
        if (search.get("error_code").equals(0)) {
            org.json.JSONObject searchResult = (org.json.JSONObject) search.get("result");
            JSONArray userList = (JSONArray) searchResult.get("user_list");
            org.json.JSONObject o = (org.json.JSONObject)userList.get(0);
//            String faceToken = searchResult.get("face_token").toString();
            int user_id = Integer.parseInt(o.get("user_id").toString());
            User user = userService.queryUserById(user_id);
            if (user == null) {
                result = new JsonResult<>(ResultType.FACE_NOT_EXIST);
                return result;
            }
            Equipment equipment = equipmentService.queryEquipmentById(equipment_id);
            if ("普通员工".equals(user.getRole()) && equipment.getLevel() > 1) {
                result = new JsonResult<>(ResultType.PERMISSION_DENIED);
                return result;
            }
            Borrow borrow = BorrowService.queryByUserIdAndEid(user_id, equipment_id);
            if (borrow == null) {
                Borrow b = new Borrow();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                b.setUser_id(user_id);
                b.setEquipment_id(equipment_id);
                b.setBorrow_date(borrow_date);
                b.setE_status("使用中");
                b.setStart_date(sdf.format(new Date()));
                BorrowService.add(b);
                //修改设备状态
                Equipment equipment1 = equipmentService.queryEquipmentById(equipment_id);
                equipment1.setStatus(0);
                equipmentService.updateEquipment(equipment1);
            }else {

            }

        } else {
            result.setMsg(search.get("error_msg").toString());
            result.setCode(3001);
        }
        return result;
    }

    @PostMapping("/faceBack")
    public JsonResult faceBack(@RequestParam String img,@RequestParam Integer borrowId,@RequestParam String reason,@RequestParam String damage) throws ParseException {
        JsonResult<String> result = new JsonResult<>(ResultType.SUCCESS);

        org.json.JSONObject search = baiduApiUtil.searchBase64(img);
        if (search.get("error_code").equals(0)) {
            org.json.JSONObject searchResult = (org.json.JSONObject) search.get("result");
            JSONArray userList = (JSONArray) searchResult.get("user_list");
            org.json.JSONObject o = (org.json.JSONObject)userList.get(0);
//            String faceToken = searchResult.get("face_token").toString();
            int user_id = Integer.parseInt(o.get("user_id").toString());
            User user = userService.queryUserById(user_id);
            if (user == null) {
                result = new JsonResult<>(ResultType.FACE_NOT_EXIST);
                return result;
            }
            Borrow borrow1 = BorrowService.queryById(borrowId);
            if (borrow1 != null) {
                borrow1.setDamage(damage);
                borrow1.setReason(reason);
                borrow1.setE_status("已归还");
                BorrowService.update(borrow1);

                //归还成功，修改设备状态
                Equipment equipment = equipmentService.queryEquipmentById(borrow1.getEquipment_id());
                equipment.setStatus(1);
                equipmentService.updateEquipment(equipment);

                checkRecordService.delete(borrow1.getEquipment_id());
            }

        } else {
            result.setMsg(search.get("error_msg").toString());
            result.setCode(3001);
        }
        return result;
    }

    @RequestMapping("/updateBorrow")
    public JsonResult updateBorrow (@RequestBody Borrow Borrow) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        BorrowService.update(Borrow);
        return result;
    }

    @RequestMapping("/deleteBorrow")
    public JsonResult deleteBorrow (@RequestParam(value="id") Integer id) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        BorrowService.delete(id);
        return result;
    }

    @RequestMapping("/queryBorrowById")
    public JsonResult queryBorrowById (@RequestParam(value="id") Integer id) {
        JsonResult<Borrow> result = new JsonResult<>(ResultType.SUCCESS);
        Borrow Borrow = BorrowService.queryById(id);
        result.setData(Borrow);
        return result;
    }

    @RequestMapping("/queryBorrowAll")
    public JsonResult queryBorrowAll () {
        JsonResult<List<Borrow>> result = new JsonResult<>(ResultType.SUCCESS);
        List<Borrow> list = BorrowService.queryAll();
        result.setData(list);
        return result;
    }

    @RequestMapping("/queryBorrowGroupBy")
    public JsonResult queryBorrowGroupBy () {
        JsonResult<List<Borrow>> result = new JsonResult<>(ResultType.SUCCESS);
        List<Borrow> list = BorrowService.queryBorrowGroupBy();
        result.setData(list);
        return result;
    }

    @RequestMapping("/addCheckRecord")
    public JsonResult addBorrow (@RequestBody CheckRecord checkRecord) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        checkRecordService.add(checkRecord);
        return result;
    }

    @RequestMapping("/CheckRecord")
    public JsonResult CheckRecord () {
        JsonResult<String> result = new JsonResult<>(ResultType.SUCCESS);
        int num = checkRecordService.queryCheckRecordAllNum(); //已借出设备数量
        int num1 = equipmentService.queryEquipmentNum();  //可用设备数量
        int num2 = equipmentService.queryEquipmentAllNum(); //设备总数量
        if ((num + num1) != num2) {
            result = new JsonResult<>(ResultType.E_NUM_FALSE);
        }
        return result;
    }

}
