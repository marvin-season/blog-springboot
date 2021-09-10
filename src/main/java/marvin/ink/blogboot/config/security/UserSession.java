package marvin.ink.blogboot.config.security;

import lombok.Data;
import marvin.ink.blogboot.model.entity.Role;

import java.util.Set;

/**
 * @Author: 马文澍
 * @Date: 2021/9/7 10:15
 */
@Data
public class UserSession {

    private Integer id;

    private String username;

    private String qq;

    private Set<Role> roles;

    private String mail;

    private String phone;

    private String wx;

}
