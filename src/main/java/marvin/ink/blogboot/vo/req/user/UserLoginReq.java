package marvin.ink.blogboot.vo.req.user;

import lombok.Data;

/**
 * @Author: 马文澍
 * @Date: 2021/8/30 20:56
 */

@Data
public class UserLoginReq {
    private String username;

    private String password;

    private String capture;
}
