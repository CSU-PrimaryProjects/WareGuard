package com.school.manager.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Date 2020/3/29
 * @auth jack
 * @Desc 权限管理拦截器
 **/
public class AuthInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        log.info("---------------------开始进入请求地址拦截----------------------------");
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
      /*  if (user == null) {
            try {
             //   response.sendRedirect("/login");
                request.getRequestDispatcher("/WEB-INF/view/login.html").forward(request, response);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            }
            return false;

        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
