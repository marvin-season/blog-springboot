package marvin.ink.blogboot.config;

import lombok.extern.slf4j.Slf4j;
import marvin.ink.blogboot.exception.CustomizeException;
import marvin.ink.blogboot.model.common.MyResponse;
import marvin.ink.blogboot.model.enums.ResultEnum;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: 马文澍
 * @Date: 2021/8/28 17:03
 * 全局异常捕获
 */
@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {
    /**
     * 请求方法不支持异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public MyResponse<?> exception(HttpRequestMethodNotSupportedException e) {
        log.warn("请求方式不支持");
        return MyResponse.is(ResultEnum.METHOD_NOT_ALLOWED);
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(CustomizeException.class)
    public MyResponse<?> exception(CustomizeException e) {
        log.warn("CustomizeException: {}", e.toString());
        return MyResponse.is(e);
    }

    @ExceptionHandler(Exception.class)
    public MyResponse<?> exception(Exception e) {
        return MyResponse.is(ResultEnum.ERROR).hint(e.getMessage());
    }
}
