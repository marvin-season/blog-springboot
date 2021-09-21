package marvin.ink.blogboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import marvin.ink.blogboot.model.entity.User;
import marvin.ink.blogboot.req.user.UserSaveReq;
import marvin.ink.blogboot.res.user.UserSearchRes;
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
    @PostMapping("avatar")
    public void updateAvatar(@RequestBody UserSaveReq req) {
        userService.updateAvatar(req.getAvatar(), req.getId());
    }

    @ApiOperation("修改用户基本信息")
    @PostMapping("update")
    public void update(@RequestBody UserSaveReq req) {
        userService.update(req);
    }


    @GetMapping("{id}")
    @ApiOperation("查询个人信息")
    public UserSearchRes findById(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }
}
