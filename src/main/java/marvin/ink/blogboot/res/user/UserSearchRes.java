package marvin.ink.blogboot.res.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import marvin.ink.blogboot.model.entity.Role;

import java.util.Set;

/**
 * @Author: 马文澍
 * @Date: 2021/9/20 15:32
 * Description: UserSearchRes
 */
@Data
public class UserSearchRes {

    private Integer id;

    private String username;

    /**
     * 账户封禁
     */
    private Boolean locked;

    private String qq;

    private String mail;

    private String phone;

    private String wx;

    /**
     * 头像
     */
    private String avatar;

    private Set<Role> roles;
}
