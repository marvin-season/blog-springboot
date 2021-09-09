package marvin.ink.blogboot.controller;

import marvin.ink.blogboot.req.user.UserRegistryReq;
import marvin.ink.blogboot.res.captcha.CaptchaRes;
import marvin.ink.blogboot.service.CaptchaService;
import marvin.ink.blogboot.service.UserService;
import org.springframework.web.bind.annotation.*;

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

    @Resource
    private CaptchaService captchaService;

    @PostMapping("registry")
    public void registry(@RequestBody UserRegistryReq userRegistryReq) {
        userService.registry(userRegistryReq);
    }

    @GetMapping("captcha")
    public CaptchaRes captcha() {
        return captchaService.get();
    }
}
