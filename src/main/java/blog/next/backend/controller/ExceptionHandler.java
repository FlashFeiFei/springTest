package blog.next.backend.controller;

import blog.next.backend.common.ApiJson;
import blog.next.backend.exception.BaseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

//spring boot mvc 异常统一处理
@ControllerAdvice
public class ExceptionHandler {

    /**
     * 自定义异常捕获
     * @param e 自定义异常
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public ApiJson exceptionHandler(BaseException e)
    {

        return ApiJson.errorJson(e.getMessage());
    }
}
