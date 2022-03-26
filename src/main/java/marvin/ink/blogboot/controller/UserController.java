package marvin.ink.blogboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import marvin.ink.blogboot.model.common.PageData;
import marvin.ink.blogboot.model.entity.User;
import marvin.ink.blogboot.req.user.UserPageSearchReq;
import marvin.ink.blogboot.req.user.UserSaveReq;
import marvin.ink.blogboot.res.user.UserSearchRes;
import marvin.ink.blogboot.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public void registry(@Validated @RequestBody UserSaveReq userSaveReq) {
        userService.registry(userSaveReq);
    }


    @ApiOperation("获取用户登录信息")
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


    @ApiOperation("查询粉丝")
    @GetMapping("fans/{userId}")
    public List<UserSearchRes> findMyFans(@PathVariable Integer userId) {
        return userService.findMyFans(userId);
    }

    @ApiOperation("查询关注")
    @GetMapping("love/{userId}")
    public List<UserSearchRes> findMyLove(@PathVariable Integer userId) {
        return userService.findMyLove(userId);
    }

    @ApiOperation("添加关注")
    @PostMapping("addLove/{whoId}/{fansId}")
    public void addLove(@PathVariable Integer whoId, @PathVariable Integer fansId) {
        userService.addLove(whoId, fansId);
    }

    @ApiOperation("取消关注")
    @PostMapping("deleteLove/{whoId}/{fansId}")
    public void deleteLove(@PathVariable Integer whoId, @PathVariable Integer fansId) {
        userService.deleteLove(whoId, fansId);
    }

    @ApiOperation("查询所有")
    @GetMapping("findAll")
    public PageData<UserSearchRes> findAll(@Validated UserPageSearchReq pageSearchReq) {
        return userService.findAll(pageSearchReq);
    }
}
