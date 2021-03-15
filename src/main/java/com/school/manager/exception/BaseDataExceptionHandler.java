package com.school.manager.exception;

import com.school.manager.model.JsonResult;
import com.school.manager.model.ResultType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BaseDataExceptionHandler {

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("请求有参数才进来");
    }
    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "harry");
    }
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e){
        JsonResult result = new JsonResult(ResultType.SUCCESS);
        //业务异常
        if(e instanceof BaseDataException){
            result.setCode(((BaseDataException) e).getCode());
            result.setMsg(e.getMessage());
        }else{
            result.setCode(ResultType.WRONG.getCode());
            result.setMsg(ResultType.WRONG.getInfo());
        }
        return result;
    }
}
