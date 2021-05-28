package blog.next.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "自定义一个403错误信息")
public class HttpException extends RuntimeException {

    public HttpException() {
    }

    public HttpException(String message) {
        super(message);
    }
}
