package marvin.ink.blogboot.vo.res.user;

import lombok.Data;
import marvin.ink.blogboot.model.entity.Role;

/**
 * @Author: 马文澍
 * @Date: 2021/8/30 21:01
 */
@Data
public class UserLoginRes {
    private String username;

    private Boolean locked;

    private String qq;

    private String mail;

    private String phone;

    private String id;

    private Role role;


}
