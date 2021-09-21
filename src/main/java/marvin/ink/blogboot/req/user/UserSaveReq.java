package marvin.ink.blogboot.req.user;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import marvin.ink.blogboot.model.entity.Role;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @Author: 马文澍
 * @Date: 2021/9/4 22:34
 */
@Data
public class UserSaveReq {
    private Integer id;

    @NotNull(message = "username 不能为空")
    private String username;

    @NotNull(message = "password 不能为空")
    private String password;

    private String qq;

    private String wx;

    private String mail;

    private String phone;
    // 头像
    private String avatar;

    private Boolean locked = false;

    private Set<Role> roles;
}
