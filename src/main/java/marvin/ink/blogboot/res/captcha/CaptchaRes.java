package marvin.ink.blogboot.res.captcha;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: 马文澍
 * @Date: 2021/9/7 14:34
 */
@Data
@Accessors(chain = true)
public class CaptchaRes {

    private String image;

    private String id;

}
