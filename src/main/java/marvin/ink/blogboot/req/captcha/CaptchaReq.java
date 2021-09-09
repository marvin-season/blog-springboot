package marvin.ink.blogboot.req.captcha;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: 马文澍
 * @Date: 2021/9/9 16:45
 */
@Data
public class CaptchaReq {

    @NotNull(message = "验证码id不能为空")
    private String id;

    @NotNull(message = "验证码不能为空")
    private String code;
}
