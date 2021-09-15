package marvin.ink.blogboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import marvin.ink.blogboot.model.entity.User;
import marvin.ink.blogboot.req.user.UserSaveReq;
import marvin.ink.blogboot.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: 马文澍
 * @Date: 2021/9/4 22:32
 */
@RestController
@RequestMapping("user")
@Api(tags = "用户接口")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("注册用户")
    @PostMapping("registry")
    public void registry(@RequestBody UserSaveReq userSaveReq) {
        userService.registry(userSaveReq);
    }


    @GetMapping("principal")
    public User principal() {
        return userService.principal();
    }

    @ApiOperation("修改该用户头像")
    @PostMapping("avatar/{id}/{avatar}")
    public void updateAvatar(@PathVariable Integer id, @PathVariable String avatar) {
        userService.updateAvatar(avatar, id);
    }

    @ApiOperation("修改用户基本信息")
    @PostMapping("update")
    public void update(@RequestBody UserSaveReq req) {
        userService.update(req);
    }
}
