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
import marvin.ink.blogboot.utils.JwtUtils;
import marvin.ink.blogboot.vo.req.user.UserLoginReq;
import marvin.ink.blogboot.vo.res.user.UserTokenRes;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 马文澍
 * @Date: 2021/9/5 16:27
 */
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    public LoginFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserLoginReq user = null;
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), UserLoginReq.class);
            log.info(user.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.notNull(user, () -> CustomizeException.is(ResultEnum.PARAM_ERROR).hint("参数错误"));

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword());
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        System.out.println(authResult);


        UserSession userSession = BeanUtil.copyProperties(authResult.getPrincipal(), UserSession.class);
        String token = jwtUtils.genToken(userSession);
        UserTokenRes userTokenRes = new UserTokenRes().setToken(JwtProperties.TOKEN_PREFIX + token).setHeader(JwtProperties.HEADER);

        try (ServletOutputStream out = response.getOutputStream()) {
            new ObjectMapper().writeValue(out, MyResponse.success(userTokenRes));
        }

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.warn("登陆失败");

        try (ServletOutputStream out = response.getOutputStream();) {
            new ObjectMapper().writeValue(out, MyResponse.is(ResultEnum.AUTHEN_ERROR).hint("登陆失败"));
        }
    }
}
