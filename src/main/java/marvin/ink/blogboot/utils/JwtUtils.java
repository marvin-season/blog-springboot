package marvin.ink.blogboot.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import marvin.ink.blogboot.config.security.JwtProperties;
import marvin.ink.blogboot.model.entity.User;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: 马文澍
 * @Date: 2021/9/5 20:01
 */
@Component
public class JwtUtils {

    @Resource
    private JwtProperties jwtProperties;

    public String genToken(Object obj) {
        Map<String, Object> map = BeanUtil.beanToMap(obj);
        return Jwts.builder()
                .setClaims(map)
                .setExpiration(DateUtil.offsetSecond(DateUtil.date(), jwtProperties.getExpireTime()))
                .signWith(SignatureAlgorithm.HS512, Base64.encode(jwtProperties.getSecret()))
                .compact();
    }

    public <T> T parseJwtToken(String token, Class<T> clazz) {
        Object body = Jwts.parser()
                .setSigningKey(Base64.encode(jwtProperties.getSecret()))
                .parse(token)
                .getBody();
        return BeanUtil.copyProperties(body, clazz);
    }

    public Authentication parseJwtToken(String token) {
        Object body = Jwts.parser()
                .setSigningKey(Base64.encode(jwtProperties.getSecret()))
                .parse(token)
                .getBody();
        User user = BeanUtil.copyProperties(body, User.class);
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }
}
