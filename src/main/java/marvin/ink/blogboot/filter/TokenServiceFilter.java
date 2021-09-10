package marvin.ink.blogboot.filter;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import marvin.ink.blogboot.config.security.JwtProperties;
import marvin.ink.blogboot.config.security.UserSession;
import marvin.ink.blogboot.exception.CustomizeException;
import marvin.ink.blogboot.model.common.MyResponse;
import marvin.ink.blogboot.model.enums.ResultEnum;
import marvin.ink.blogboot.req.user.UserLoginReq;
import marvin.ink.blogboot.res.user.UserRes;
import marvin.ink.blogboot.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author: 马文澍
 * @Date: 2021/9/5 16:27
 */
@Slf4j
@Component
public class TokenServiceFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserLoginReq user;
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), UserLoginReq.class);
        } catch (IOException e) {
            log.info("参数解析出错");
            throw CustomizeException.is(ResultEnum.PARAM_ERROR).hint("参数解析出错");
        }
        Assert.notNull(user, () -> CustomizeException.is(ResultEnum.PARAM_ERROR).hint("参数解析出错"));

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword(), new ArrayList<>());
        return super.getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {

        UserSession userSession = BeanUtil.copyProperties(authResult.getPrincipal(), UserSession.class);
        String token = jwtUtils.genToken(userSession);

        UserRes userRes = new UserRes()
                .setUserSession(userSession)
                .setToken(JwtProperties.TOKEN_PREFIX + token)
                .setHeader(JwtProperties.HEADER);
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");


        try (ServletOutputStream out = response.getOutputStream()) {
            objectMapper.writeValue(out, MyResponse.success(userRes));
        }

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.warn("登陆失败");
        response.setContentType("application/json;charset=utf-8");
        try (ServletOutputStream out = response.getOutputStream();) {
            objectMapper.writeValue(out, MyResponse.is(ResultEnum.AUTHEN_ERROR).hint("登录失败"));
        }
    }
}
