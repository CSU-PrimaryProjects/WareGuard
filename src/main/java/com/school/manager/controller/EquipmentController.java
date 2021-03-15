package com.school.manager.controller;

import com.school.manager.model.Equipment;
import com.school.manager.service.EquipmentService;
import com.github.pagehelper.PageInfo;
import com.school.manager.common.PageDataDto;
import com.school.manager.model.JsonResult;
import com.school.manager.model.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class EquipmentController {

    @Autowired
    private EquipmentService EquipmentService;


    @RequestMapping("/queryEquipmentList")
    public JsonResult queryEquipmentList (@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                     Equipment Equipment) {
        JsonResult<PageDataDto> result = new JsonResult<PageDataDto>(ResultType.SUCCESS);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        PageInfo<Equipment> EquipmentPageInfo = EquipmentService.queryEquipmentList(Equipment,pageInfo);
        List<Equipment> list = EquipmentPageInfo.getList();
        PageDataDto pageDataDto = new PageDataDto(EquipmentPageInfo.getTotal(),
                list,
                EquipmentPageInfo.getPageNum(),
                EquipmentPageInfo.getPageSize(),
                EquipmentPageInfo.getPages());
        result.setData(pageDataDto);
        return result;
    }

    @RequestMapping("/queryEquipmentList1")
    public JsonResult queryEquipmentList1 (@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                          @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                          Equipment Equipment) {
        JsonResult<PageDataDto> result = new JsonResult<PageDataDto>(ResultType.SUCCESS);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        PageInfo<Equipment> EquipmentPageInfo = EquipmentService.queryEquipmentList1(Equipment,pageInfo);
        List<Equipment> list = EquipmentPageInfo.getList();
        PageDataDto pageDataDto = new PageDataDto(EquipmentPageInfo.getTotal(),
                list,
                EquipmentPageInfo.getPageNum(),
                EquipmentPageInfo.getPageSize(),
                EquipmentPageInfo.getPages());
        result.setData(pageDataDto);
        return result;
    }

    @RequestMapping("/addEquipment")
    public JsonResult addEquipment (@RequestBody Equipment Equipment) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        EquipmentService.addEquipment(Equipment);
        return result;
    }

    @RequestMapping("/updateEquipment")
    public JsonResult updateEquipment (@RequestBody Equipment Equipment) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        EquipmentService.updateEquipment(Equipment);
        return result;
    }

    @RequestMapping("/deleteEquipment")
    public JsonResult deleteEquipment (@RequestParam(value="id") Integer id) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        EquipmentService.deleteEquipment(id);
        return result;
    }

    @RequestMapping("/queryEquipmentById")
    public JsonResult queryEquipmentById (@RequestParam(value="id") Integer id) {
        JsonResult<Equipment> result = new JsonResult<>(ResultType.SUCCESS);
        Equipment Equipment = EquipmentService.queryEquipmentById(id);
        result.setData(Equipment);
        return result;
    }

    @RequestMapping("/queryEquipmentAll")
    public JsonResult queryEquipmentAll (@RequestParam(value="name") String name) {
        JsonResult<List<Equipment>> result = new JsonResult<>(ResultType.SUCCESS);
        List<Equipment> list = EquipmentService.queryEquipmentAll(name);
        result.setData(list);
        return result;
    }


    @RequestMapping("/queryByGroup")
    public JsonResult queryByGroup () {
        JsonResult<List<Equipment>> result = new JsonResult<>(ResultType.SUCCESS);
        List<Equipment> list = EquipmentService.queryByGroup();
        result.setData(list);
        return result;
    }

    /**
     * 归还图书
     * @param id
     * @return
     */
    @RequestMapping("/updateEquipmentStatus")
    public JsonResult updateEquipmentStatus (@RequestParam(value="id") Integer id) {
        JsonResult<String> result = new JsonResult<>(ResultType.SUCCESS);
        Equipment Equipment = EquipmentService.queryEquipmentById(id);
        if (Equipment != null) {
            EquipmentService.updateEquipment(Equipment);
        }
        return result;
    }

    //图片上传
    @RequestMapping("upload")
    public JsonResult upload(MultipartFile file, HttpServletRequest request){
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        String prefix="";
        String dateStr="";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        HttpSession session = request.getSession();
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                Date date = new Date();
                String uuid = UUID.randomUUID()+"";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
                //      String filepath = "D:\\mycode\\LayUiTest\\src\\main\\resources\\static\\images\\" + dateStr+"\\"+uuid+"." + prefix;
                //    String filepath = "D:\\image"; webapp\WEB-INF\static
           //     String filepath = System.getProperty("user.dir") + "\\src\\main\\webapp\\WEB-INF\\static\\img\\"+originalName;
                String realPath = request.getRealPath("/");  //获取磁盘根路径
                String substring = realPath.substring(0, realPath.indexOf("out"));
                String filepath = substring + "\\src\\main\\webapp\\WEB-INF\\static\\img\\"+originalName;
                File files=new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
                session.setAttribute("imgpath","/static/img/"+originalName);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        return result;
    }


}
