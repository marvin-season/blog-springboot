package marvin.ink.blogboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: 马文澍
 * @Date: 2021/9/7 14:38
 */
@Component
@Data
@ConfigurationProperties(prefix = "captcha")
public class CaptchaProperties {
    private int width;

    private int height;

    private Long expireTime;
}
