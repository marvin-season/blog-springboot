package marvin.ink.blogboot.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import marvin.ink.blogboot.model.common.MyResponse;
import marvin.ink.blogboot.model.enums.ResultEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未认证访问
 */
@Component
public class MyAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        try (ServletOutputStream out = response.getOutputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            out.write(objectMapper.writeValueAsBytes(MyResponse.is(ResultEnum.AUTHEN_ERROR).hint("请先登录")));
        }
    }
}
