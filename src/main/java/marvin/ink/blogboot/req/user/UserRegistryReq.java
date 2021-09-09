package marvin.ink.blogboot.req.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: 马文澍
 * @Date: 2021/9/4 22:34
 */
@Data
public class UserRegistryReq {
    @NotNull(message = "username 不能为空")
    private String username;

    @NotNull(message = "password 不能为空")
    private String password;

    private String qq;

    private String wx;

    private String mail;

    private String phone;
}
