package marvin.ink.blogboot.vo.req.user;

import lombok.Data;

/**
 * @Author: 马文澍
 * @Date: 2021/9/4 22:34
 */
@Data
public class UserRegistryReq {
    private String username;

    private String password;
    /**
     * 账户封禁
     */
    private Boolean locked;

    private String qq;

    private String mail;

    private String phone;
}
