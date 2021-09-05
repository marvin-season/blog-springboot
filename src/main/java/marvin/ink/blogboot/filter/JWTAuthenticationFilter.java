package marvin.ink.blogboot.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import marvin.ink.blogboot.config.security.JwtProperties;
import marvin.ink.blogboot.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Author: 马文澍
 * @Date: 2021/9/5 20:40
 */
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private final JwtUtils jwtUtils;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        super(authenticationManager);
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String header = ServletUtil.getHeader(request, JwtProperties.HEADER, StandardCharsets.UTF_8);
            if (ObjectUtil.isNotEmpty(header) && header.startsWith(JwtProperties.TOKEN_PREFIX)) {
                String replace = header.replace(JwtProperties.TOKEN_PREFIX, StrUtil.EMPTY);
                Authentication authentication = jwtUtils.parseJwtToken(replace);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            throw new SessionAuthenticationException(e.getMessage());
        } finally {
            chain.doFilter(request, response);
        }
    }
}
