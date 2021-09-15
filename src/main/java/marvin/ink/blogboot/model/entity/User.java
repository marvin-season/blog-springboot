package marvin.ink.blogboot.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import marvin.ink.blogboot.model.common.BaseEntity;
import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @Author: 马文澍
 * @Date: 2021/8/26 20:37
 */
@Data
@Alias("Author")
public class User extends BaseEntity implements UserDetails {

    private String username;

    private String password;
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

    @TableField(exist = false)
    private Set<Role> roles;

    @TableField(exist = false)
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
