package marvin.ink.blogboot.res.user;

import lombok.Data;
import lombok.experimental.Accessors;
import marvin.ink.blogboot.config.security.UserSession;

/**
 * @Author: 马文澍
 * @Date: 2021/8/30 21:22
 */
@Data
@Accessors(chain = true)
public class UserTokenRes {
    private String token;

    private String header;
}
