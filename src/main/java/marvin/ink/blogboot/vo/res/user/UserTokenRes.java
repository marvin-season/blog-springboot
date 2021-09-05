package marvin.ink.blogboot.vo.res.user;

import lombok.Data;
import lombok.experimental.Accessors;

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
