package marvin.ink.blogboot.config;

import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.extern.slf4j.Slf4j;
import marvin.ink.blogboot.exception.CustomizeException;
import marvin.ink.blogboot.model.common.MyResponse;
import marvin.ink.blogboot.model.enums.ResultEnum;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 马文澍
 * @Date: 2021/8/28 17:03
 * 全局异常捕获
 */
@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler(UsernameNotFoundException.class)
    public MyResponse<?> exception(UsernameNotFoundException exception) {
        log.warn(exception.getMessage());
        return MyResponse.is(ResultEnum.AUTHEN_ERROR).hint("用户不存在，或者账户密码错误");
    }

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public MyResponse<?> exception(MethodArgumentNotValidException e) {

        e.getBindingResult().getAllErrors().forEach(o -> log.warn("{}", o.getDefaultMessage()));
        List<String> collect = e
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        String hint = CollUtil.join(collect, "，");
        return MyResponse.is(ResultEnum.PARAM_ERROR).hint(hint);
    }

    @ExceptionHandler(UnrecognizedPropertyException.class)
    public MyResponse<?> exception(UnrecognizedPropertyException e) {
        log.warn("{}", e.toString());
        return MyResponse.is(ResultEnum.ERROR).hint(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public MyResponse<?> exception(Exception e) {
        log.warn("{}", e.toString());
        return MyResponse.is(ResultEnum.ERROR).hint(e.getMessage());
    }
}
