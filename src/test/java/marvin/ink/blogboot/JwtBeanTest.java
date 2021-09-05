package marvin.ink.blogboot;

import cn.hutool.core.bean.BeanUtil;
import marvin.ink.blogboot.config.security.JwtProperties;
import marvin.ink.blogboot.utils.JwtUtils;
import marvin.ink.blogboot.vo.req.user.UserLoginReq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author: 马文澍
 * @Date: 2021/9/5 20:31
 */
@SpringBootTest
public class JwtBeanTest {

    @Autowired
    private JwtUtils jwtUtils;

    @Resource
    private JwtProperties jwtProperties;

    @Test
    public void test1(){
        System.out.println(jwtProperties.getSecret());
        System.out.println(jwtProperties.getExpireTime());
    }
    @Test
    public void test2() {
        UserLoginReq userLoginReq = new UserLoginReq();
        userLoginReq.setUsername("马文澍");
        userLoginReq.setPassword("123456");
        String token = jwtUtils.genToken(BeanUtil.beanToMap(userLoginReq));
        System.out.println(token);
    }

    @Test
    public void test3() {
        UserLoginReq userLoginReq = jwtUtils.parseJwtToken("eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6IumprOaWh-a-jSIsInBhc3N3b3JkIjoiMTIzNDU2IiwiZXhwIjoxNjMwODYzOTcwfQ.HvpqXaQaXk1CvrrKz2nH8WIGcf2V5Cq97Vtdg5JQ0Xu4HZneCuduF130AHI1xJPAcOLxGNVnhGjrxO9j1ThYUQ", UserLoginReq.class);
        System.out.println(userLoginReq);
    }
}
