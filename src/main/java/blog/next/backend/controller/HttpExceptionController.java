package blog.next.backend.controller;

import blog.next.backend.exception.HttpException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/exception"})
public class HttpExceptionController {

    @GetMapping("/exception")
    public String exception() {
        throw new HttpException("自定义的httpException 403异常问题");
    }
}
