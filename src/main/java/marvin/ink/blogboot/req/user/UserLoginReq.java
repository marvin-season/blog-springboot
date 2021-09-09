package marvin.ink.blogboot.req.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: 马文澍
 * @Date: 2021/8/30 20:56
 */

@Data
public class UserLoginReq {

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;

    @NotNull(message = "验证码不能为空")
    private String captcha;

    @NotNull(message = "验证码id不能为空")
    private String captchaId;
}
