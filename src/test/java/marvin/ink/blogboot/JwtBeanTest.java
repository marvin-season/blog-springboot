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
        UserLoginReq userLoginReq = jwtUtils.parseJwtToken("eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6IumprOaWh-a-jSIsInBhc3N3b3JkIjoiJDJhJDEwJDFYSlllclNPT0dWSG9BNmVEVG83ZmVHT04vS2IzdDhKRGdGZlQ3Q3M5MmtZN09aaGFaYWwuIiwibG9ja2VkIjpmYWxzZSwicXEiOiIyNzY0ODc2NTc5IiwibWFpbCI6IjI3NjQ4NzY1NzlAcXEuY29tIiwicGhvbmUiOiIxNTYyMzE5MjcxNyIsInd4IjpudWxsLCJyb2xlcyI6bnVsbCwiYXV0aG9yaXRpZXMiOm51bGwsImlkIjozLCJjcmVhdGVUaW1lIjp7InllYXIiOjIwMjEsIm1vbnRoVmFsdWUiOjksIm1vbnRoIjoiU0VQVEVNQkVSIiwiZGF5T2ZNb250aCI6NCwiZGF5T2ZZZWFyIjoyNDcsImRheU9mV2VlayI6IlNBVFVSREFZIiwiaG91ciI6MjIsIm1pbnV0ZSI6NDksInNlY29uZCI6MTUsIm5hbm8iOjAsImNocm9ub2xvZ3kiOnsiaWQiOiJJU08iLCJjYWxlbmRhclR5cGUiOiJpc284NjAxIn19LCJ1cGRhdGVUaW1lIjp7InllYXIiOjIwMjEsIm1vbnRoVmFsdWUiOjksIm1vbnRoIjoiU0VQVEVNQkVSIiwiZGF5T2ZNb250aCI6NCwiZGF5T2ZZZWFyIjoyNDcsImRheU9mV2VlayI6IlNBVFVSREFZIiwiaG91ciI6MjIsIm1pbnV0ZSI6NDksInNlY29uZCI6MTUsIm5hbm8iOjAsImNocm9ub2xvZ3kiOnsiaWQiOiJJU08iLCJjYWxlbmRhclR5cGUiOiJpc284NjAxIn19LCJkZWxldGVkIjowLCJ1dWlkIjoiZjAxZGI4OGYtYmQ1YS00MDAyLWI4ODgtMmU3ZTQxNWNkNTViIiwiZXhwIjoxNjMwOTE5NzI4fQ.MKIAyDZNAYyg4SYJXFW1sZr3RbHyauWLNJz8Acu_piDbZu_OtYtwX5fDEvqNd_GNPDoR_Nm310HpWl4cTqp1ag", UserLoginReq.class);
        System.out.println(userLoginReq);
    }
}
