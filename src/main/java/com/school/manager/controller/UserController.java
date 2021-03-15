package com.school.manager.controller;

import com.github.pagehelper.PageInfo;
import com.school.manager.common.CaptchaImgWrapper;
import com.school.manager.common.PageDataDto;
import com.school.manager.model.Emp;
import com.school.manager.model.JsonResult;
import com.school.manager.model.ResultType;
import com.school.manager.model.User;
import com.school.manager.service.UserService;
import com.school.manager.util.BaiduApiUtil;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.util.List;

/**
 * @author jack
 * @date 2020/3/24 20:59
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BaiduApiUtil baiduApiUtil;

    /**
     * 登录验证
     * @param user
     * @return
     */
    @RequestMapping("/userLogin")
    public JsonResult login (@RequestBody User user) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        User user1 = userService.queryByName(user.getName());
        if (user1 == null) {
            result = new JsonResult<String>(ResultType.USER_NOT_EXIST);
        }else {
            if (!user.getPassword().equals(user1.getPassword())) {
                result = new JsonResult<String>(ResultType.PWSSWORD_ERROR);
            }else {
                //登录成功用户信息放到session
                session.setAttribute("user",user1.getName());
                session.setAttribute("userId",user1.getId());
                result.setData(user1.getName());
            }
        }
        return result;
    }

    @RequestMapping("/queryUserList")
    public JsonResult queryUserList (@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                     User res) {
        JsonResult<PageDataDto> result = new JsonResult<PageDataDto>(ResultType.SUCCESS);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        PageInfo<User> carPageInfo = userService.queryUserList(res,pageInfo);
        List<User> resList = carPageInfo.getList();
        PageDataDto pageDataDto = new PageDataDto(carPageInfo.getTotal(),
                resList,
                carPageInfo.getPageNum(),
                carPageInfo.getPageSize(),
                carPageInfo.getPages());
        result.setData(pageDataDto);
        return result;
    }

    @RequestMapping("/addUser")
    public JsonResult addUser (@RequestBody User user) {
        JsonResult<String> result = new JsonResult<String>(ResultType.SUCCESS);
        User user1 = userService.queryByName(user.getName());
        if (user1 != null) {
            result.setCode(ResultType.USER_EXIST.getCode());
            result.setMsg(ResultType.USER_EXIST.getInfo());
            return result;
        }
        userService.add(user);
        return result;
    }

    @RequestMapping("/faceRegister")
    public JsonResult faceRegister(@RequestBody User user) {
        JsonResult<String> result = new JsonResult<>(ResultType.SUCCESS);
        User user1 = userService.queryByName(user.getName());
        if (user1 != null) {
            result = new JsonResult<>(ResultType.USER_EXIST);
            return result;
        }
        org.json.JSONObject search = baiduApiUtil.searchBase64(user.getImg());
        if (search.get("error_code").equals(0)) {
            result = new JsonResult<>(ResultType.STUDENT_EXIST);
            return result;
        }
        userService.add(user);
        User user2 = userService.queryByName(user.getName());
        org.json.JSONObject register = baiduApiUtil.register(String.valueOf(user2.getId()), user.getImg(), BaiduApiUtil.IMG_TYPE_BASE64, String.valueOf(user2.getId()), "user");
        if (register.get("error_code").equals(0)) {
            org.json.JSONObject registerResult = (org.json.JSONObject) register.get("result");
            user2.setFace_token(registerResult.getString("face_token"));
            userService.update(user2);
        } else {
            result.setMsg(register.get("error_msg").toString());
            result.setCode(3001);
            userService.delete(user2.getId());
        }
        return result;
    }
    @RequestMapping("/deleteFace")
    public JsonResult deleteFace () {
        JsonResult<Emp> result = new JsonResult<Emp>(ResultType.SUCCESS);
        baiduApiUtil.deleteFace("6", "user", "638f283655e6c9066232741aa1817854");
        return result;
    }
    @RequestMapping("/updateUser")
    public JsonResult updateUser (@RequestBody User user) {
        JsonResult<User> result = new JsonResult<User>(ResultType.SUCCESS);
        userService.update(user);
        return result;
    }

    @RequestMapping("/deleteUser")
    public JsonResult deleteResult (@RequestParam(value = "id") Integer id) {
        JsonResult<User> result = new JsonResult<User>(ResultType.SUCCESS);
        userService.delete(id);
        return result;
    }
    @RequestMapping("/updatePassword")
    public JsonResult updatePassword (@RequestBody User user) {
        JsonResult<User> result = new JsonResult<User>(ResultType.SUCCESS);
        User user1 = userService.queryByName(user.getName());
        if (user1 != null) {
            user1.setPassword(user.getPassword());
        }
        userService.update(user1);
        return result;
    }

    @RequestMapping("/queryUserById")
    public JsonResult queryUserById (@RequestParam(value = "id") Integer id) {
        JsonResult<User> result = new JsonResult<User>(ResultType.SUCCESS);
        User user = userService.queryUserById(id);
        result.setData(user);
        return result;
    }


    @RequestMapping("/queryUserByUsername")
    public JsonResult queryUserByUsername (@RequestParam(value = "username") String username) {
        JsonResult<User> result = new JsonResult<User>(ResultType.SUCCESS);
        User user1 = userService.queryByName(username);
        result.setData(user1);
        return result;
    }

    @RequestMapping(value = "/sendImg", method = RequestMethod.GET)
    public void sendImg(Integer width, Integer height, HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        HttpSession session = req.getSession();
        if (width == null || height == null) {
            throw new InvalidDataException("数据效验为空");
        }
        //四位随机码
        String captcha = CaptchaImgWrapper.generateCaptch(4);
        RenderedImage image = CaptchaImgWrapper.generatePic(width, height, captcha);
        // 调用工具类生成的验证码和验证码图片
        session.setAttribute("imgCode", captcha);
        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", -1);
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos;
        sos = resp.getOutputStream();
        ImageIO.write(image, "png", sos);
        sos.close();
    }

    @RequestMapping(value = "/verifyCode")
    public JsonResult<String> verifyImg(@RequestParam(value = "code") String code, HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        String imgCode = (String) session.getAttribute("imgCode");
        JsonResult<String> result = new JsonResult<>(ResultType.SUCCESS);
        if (code != null && imgCode != null && code.equalsIgnoreCase(imgCode)) {// 不区分大小写
            result.setData("验证码正确");
        } else {// 验证码错误或者数据为空
            result = new JsonResult<String>(ResultType.VCODE_ERROR);
        }
        return result;
    }
}
