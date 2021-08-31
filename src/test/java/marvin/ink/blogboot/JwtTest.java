package marvin.ink.blogboot;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashMap;

/**
 * @Author: 马文澍
 * @Date: 2021/8/30 23:41
 */
public class JwtTest {
    @Test
    public void genToken() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("qq", "2764876579");
        String token = Jwts.builder()
                .setClaims(map)
                .setExpiration(DateUtil.offsetSecond(DateUtil.date(), 300))
                .signWith(SignatureAlgorithm.HS512, Base64.encode("12345"))
                .compact();
        System.out.println(token);
    }

    @Test
    public void parseJwtToken() {
        Object body = Jwts.parser()
                .setSigningKey(Base64.encode("12345"))
                .parse("eyJhbGciOiJIUzUxMiJ9.eyJxcSI6IjI3NjQ4NzY1NzkiLCJuYW1lIjoi5byg5LiJIiwiZXhwIjoxNjMwMzM4OTI5fQ.zioXY1Ccu05oNQrKThYIBe-A1OHv_ZsGamstfzJptlxN2hEuKa6_0iVYssX7WsDLpKNin6eQ3wSaG4D3Wou1Ng")
                .getBody();
        System.out.println(body);
    }
}
