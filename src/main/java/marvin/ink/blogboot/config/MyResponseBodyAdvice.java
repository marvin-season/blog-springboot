package marvin.ink.blogboot.config;

import lombok.extern.slf4j.Slf4j;
import marvin.ink.blogboot.model.common.MyResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

/**
 * @Author: 马文澍
 * @Date: 2021/8/28 22:05
 * 对结果进行统一处理
 */
@RestControllerAdvice
@Slf4j
public class MyResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        Method method = methodParameter.getMethod();
        if (method == null) {
            return false;
        }

        Class<?> declaringClass = methodParameter.getDeclaringClass();
        // 检查过滤swagger包路径
        if (declaringClass.getName().contains("springfox.documentation")) {
            return false;
        }

        //如果类上有注解，不拦截
//        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreGlobalResultHandler.class)) {
//            return false;
//        }
//        // 方法上有注解不拦截
//        if (methodParameter.getMethod().isAnnotationPresent(IgnoreGlobalResultHandler.class)) {
//            return false;
//        }

        // 如果方法返回类型不等于包装类型，就拦截包装起来
//        return !CommonJsonResponse.class.equals(method.getReturnType());
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof String || body instanceof MyResponse<?>) {
            return body;
        }
        // 防止返回类型不是包装类型，但是抛出异常，被处理成包装类型
//        if (body instanceof CommonJsonResponse) {
//            return body;
//        }
        return MyResponse.success(body);
    }
}
