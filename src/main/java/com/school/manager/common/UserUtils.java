package com.school.manager.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserUtils {
    public static String getUserName () {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String username = "";
        if (session.getAttribute("user") != null) {
            username =  (String) session.getAttribute("user");
        }
        return username;
    }

    public static Integer getUserId () {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        Integer userId = 0;
        if (session.getAttribute("userId") != null) {
            userId = (Integer) session.getAttribute("userId");
        }
        return userId;
    }
}
