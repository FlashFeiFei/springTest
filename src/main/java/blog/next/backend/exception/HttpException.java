package blog.next.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//抛出的异常，底层会根据状态码，重定向到4xx、5xx页面，如果是接口那就是json数据
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "自定义一个403错误信息")
public class HttpException extends RuntimeException {

    public HttpException() {
    }

    public HttpException(String message) {
        super(message);
    }
}
