package marvin.ink.blogboot.config.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: 马文澍
 * @Date: 2021/9/5 20:03
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    public static final String HEADER = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";

    private int expireTime;

    private String secret;
}
