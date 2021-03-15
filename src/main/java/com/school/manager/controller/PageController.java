package com.school.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author jack
 * @date 2020/3/26 20:41
 */
@Controller
public class PageController {

    /**
     * 跳转到登录页面
     * @param mv
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login (ModelAndView mv) {
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping("/screen")
    public ModelAndView screen (ModelAndView mv) {
        mv.setViewName("admin/screen");
        return mv;
    }

    /**
     * 注册
     * @param mv
     * @return
     */
    @RequestMapping("/signup")
    public ModelAndView signup (ModelAndView mv) {
        mv.setViewName("admin/signup");
        return mv;
    }
    @RequestMapping("/logout")
    public ModelAndView logout (ModelAndView mv) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        mv.setViewName("login");
        return mv;
    }

    /**
     * 管理员登录成功跳转到主页面
     * @param mv
     * @return
     */
    @RequestMapping("/main")
    public ModelAndView main (ModelAndView mv) {
        mv.setViewName("main");
        return mv;
    }
    /**
     * 普通用户登录成功跳转到主页面
     * @param mv
     * @return
     */
    @RequestMapping("/main_1")
    public ModelAndView main_1 (ModelAndView mv) {
        mv.setViewName("main_1");
        return mv;
    }

    @RequestMapping("/index")
    public ModelAndView index (ModelAndView mv) {
        mv.setViewName("index");
        return mv;
    }



    @RequestMapping("/equipment")
    public ModelAndView images (ModelAndView mv) {
        mv.setViewName("user/equipment");
        return mv;
    }

    @RequestMapping("/borrow")
    public ModelAndView borrow (ModelAndView mv) {
        mv.setViewName("user/borrow");
        return mv;
    }

    @RequestMapping("/back")
    public ModelAndView back (ModelAndView mv) {
        mv.setViewName("user/back");
        return mv;
    }

    @RequestMapping("/fineList")
    public ModelAndView fineList (ModelAndView mv) {
        mv.setViewName("admin/fineList");
        return mv;
    }
    @RequestMapping("/fineAdd")
    public ModelAndView fineAdd (ModelAndView mv) {
        mv.setViewName("admin/fineAdd");
        return mv;
    }
    @RequestMapping("/fineUpdate/{id}")
    public ModelAndView fineUpdate (ModelAndView mv, @PathVariable(value = "id") Integer id) {
        mv.setViewName("admin/fineUpdate");
        mv.addObject("fineId",id);
        return mv;
    }

    @RequestMapping("/fine")
    public ModelAndView fine (ModelAndView mv) {
        mv.setViewName("user/fine");
        return mv;
    }

    @RequestMapping("/appealList")
    public ModelAndView appealList (ModelAndView mv) {
        mv.setViewName("admin/appealList");
        return mv;
    }

    @RequestMapping("/appeal")
    public ModelAndView appeal (ModelAndView mv) {
        mv.setViewName("user/appeal");
        return mv;
    }

    @RequestMapping("/appealAdd")
    public ModelAndView appealAdd (ModelAndView mv) {
        mv.setViewName("user/appealAdd");
        return mv;
    }


    @RequestMapping("/renewList")
    public ModelAndView renewList (ModelAndView mv) {
        mv.setViewName("admin/renewList");
        return mv;
    }
    @RequestMapping("/renewList1")
    public ModelAndView renewList1 (ModelAndView mv) {
        mv.setViewName("user/renewList1");
        return mv;
    }
    @RequestMapping("/renew")
    public ModelAndView renew (ModelAndView mv) {
        mv.setViewName("user/renew");
        return mv;
    }

    @RequestMapping("/zhuanjie")
    public ModelAndView zhuanjie (ModelAndView mv) {
        mv.setViewName("user/zhuanjie");
        return mv;
    }

    @RequestMapping("/importance")
    public ModelAndView importance (ModelAndView mv) {
        mv.setViewName("user/importance");
        return mv;
    }

    @RequestMapping("/borrowIn")
    public ModelAndView borrowIn (ModelAndView mv) {
        mv.setViewName("user/borrow_in");
        return mv;
    }

    @RequestMapping("/borrowOut")
    public ModelAndView borrowOut (ModelAndView mv) {
        mv.setViewName("user/borrow_out");
        return mv;
    }

    @RequestMapping("/face")
    public ModelAndView face (ModelAndView mv) {
        mv.setViewName("user/face");
        return mv;
    }
    @RequestMapping("/equipmentList")
    public ModelAndView equipmentList (ModelAndView mv) {
        mv.setViewName("admin/equipmentList");
        return mv;
    }
    @RequestMapping("/equipmentAdd")
    public ModelAndView equipmentAdd (ModelAndView mv) {
        mv.setViewName("admin/equipmentAdd");
        return mv;
    }

    @RequestMapping("/equipmentUpdate/{id}")
    public ModelAndView equipmentUpdate (ModelAndView mv, @PathVariable(value = "id") Integer id) {
        mv.setViewName("admin/equipmentUpdate");
        mv.addObject("bookId",id);
        return mv;
    }

    @RequestMapping("/empList")
    public ModelAndView empList (ModelAndView mv) {
        mv.setViewName("admin/empList");
        return mv;
    }
    @RequestMapping("/empAdd")
    public ModelAndView empAdd (ModelAndView mv) {
        mv.setViewName("admin/empAdd");
        return mv;
    }

    /**
     * 个人信息修改页面
     * @param mv
     * @return
     */
    @RequestMapping("/userInfo")
    public ModelAndView userInfo (ModelAndView mv) {
        mv.setViewName("user/userInfo");
        return mv;
    }

    /**
     * 借出记录页面
     * @param mv
     * @return
     */
    @RequestMapping("/recordList")
    public ModelAndView recordList (ModelAndView mv) {
        mv.setViewName("user/recordList");
        return mv;
    }

    @RequestMapping("/recordList1")
    public ModelAndView recordList1 (ModelAndView mv) {
        mv.setViewName("admin/recordList1");
        return mv;
    }

    @RequestMapping("/applyBook")
    public ModelAndView applyBook (ModelAndView mv) {
        mv.setViewName("user/applyBook");
        return mv;
    }

    @RequestMapping("/applyBookList")
    public ModelAndView applyBookList (ModelAndView mv) {
        mv.setViewName("user/applyBookList");
        return mv;
    }

    @RequestMapping("/approveList")
    public ModelAndView approveList (ModelAndView mv) {
        mv.setViewName("admin/approveList");
        return mv;
    }

    @RequestMapping("/mapLocation")
    public ModelAndView mapLocation (ModelAndView mv) {
        mv.setViewName("admin/mapLocation");
        return mv;
    }

    /**
     * 用户列表页面
     * @param mv
     * @return
     */
    @RequestMapping("/userList")
    public ModelAndView userList (ModelAndView mv) {
        mv.setViewName("admin/userList");
        return mv;
    }
    /**
     * 新增用户页面
     * @param mv
     * @return
     */
    @RequestMapping("/userAdd")
    public ModelAndView userAdd (ModelAndView mv) {
        mv.setViewName("admin/userAdd");
        return mv;
    }

    @RequestMapping("/userUpdate/{id}")
    public ModelAndView userUpdate (ModelAndView mv, @PathVariable(value = "id") Integer id) {
        mv.setViewName("admin/userUpdate");
        mv.addObject("userId",id);
        return mv;
    }

    @RequestMapping("/newsList")
    public ModelAndView newsList (ModelAndView mv) {
        mv.setViewName("admin/newsList");
        return mv;
    }

    @RequestMapping("/newsAdd")
    public ModelAndView newsAdd (ModelAndView mv) {
        mv.setViewName("admin/newsAdd");
        return mv;
    }

    @RequestMapping("/newsUpdate/{id}")
    public ModelAndView newsUpdate (ModelAndView mv, @PathVariable(value = "id") Integer id) {
        mv.setViewName("admin/newsUpdate");
        mv.addObject("newsId",id);
        return mv;
    }

    @RequestMapping("/record")
    public ModelAndView record (ModelAndView mv) {
        mv.setViewName("admin/record");
        return mv;
    }

    @RequestMapping("/recommend")
    public ModelAndView recommend (ModelAndView mv) {
        mv.setViewName("user/recommend");
        return mv;
    }

    @RequestMapping("/changePassword")
    public ModelAndView changePassword (ModelAndView mv) {
        mv.setViewName("changePassword");
        return mv;
    }
}
