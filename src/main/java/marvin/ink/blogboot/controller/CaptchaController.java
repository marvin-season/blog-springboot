package marvin.ink.blogboot.controller;

import cn.hutool.core.lang.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import marvin.ink.blogboot.exception.CustomizeException;
import marvin.ink.blogboot.model.enums.ResultEnum;
import marvin.ink.blogboot.req.captcha.CaptchaReq;
import marvin.ink.blogboot.res.captcha.CaptchaRes;
import marvin.ink.blogboot.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 马文澍
 * @Date: 2021/9/9 16:43
 */
@RestController
@RequestMapping("captcha")
@Api(tags = "验证码管理接口")
public class CaptchaController {
    public CaptchaService captchaService;

    @Autowired
    public void setCaptchaService(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @ApiOperation("验证码获取")
    @GetMapping("captcha")
    public CaptchaRes captcha() {
        return captchaService.get();
    }

    @ApiOperation("验证码验证")
    @PostMapping("verify")
    public void captcha(@RequestBody @Validated CaptchaReq captchaReq) {
        Assert.isTrue(captchaService.verify(captchaReq.getId(), captchaReq.getCode()),
                () -> CustomizeException.is(ResultEnum.CAPTCHA_ERROR).hint("验证码错误"));
    }
}
