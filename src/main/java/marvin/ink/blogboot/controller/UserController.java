package marvin.ink.blogboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import marvin.ink.blogboot.service.UserService;
import marvin.ink.blogboot.vo.req.user.UserLoginReq;
import marvin.ink.blogboot.vo.res.user.UserLoginTokenRes;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 马文澍
 * @Date: 2021/8/29 20:30
 */
@RestController
@RequestMapping("user")
@Api(tags = "用户管理接口")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("登录")
    @PostMapping("login")
    public UserLoginTokenRes login(@RequestBody @Validated UserLoginReq loginReq) {
        return null;
    }
}
