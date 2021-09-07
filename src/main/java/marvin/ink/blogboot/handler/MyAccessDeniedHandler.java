package marvin.ink.blogboot.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import marvin.ink.blogboot.model.common.MyResponse;
import marvin.ink.blogboot.model.enums.ResultEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        try (ServletOutputStream out = response.getOutputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            out.write(objectMapper.writeValueAsBytes(MyResponse.is(ResultEnum.AUTHEN_ERROR).hint("登录失败")));
        }
    }
}
