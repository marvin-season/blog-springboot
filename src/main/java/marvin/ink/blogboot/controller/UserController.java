package marvin.ink.blogboot.controller;

import marvin.ink.blogboot.service.UserService;
import marvin.ink.blogboot.vo.req.user.UserRegistryReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 马文澍
 * @Date: 2021/9/4 22:32
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("registry")
    public void registry(@RequestBody UserRegistryReq userRegistryReq) {
        userService.registry(userRegistryReq);
    }
}
