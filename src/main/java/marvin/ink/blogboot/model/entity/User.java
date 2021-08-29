package marvin.ink.blogboot.model.entity;

import lombok.Data;
import marvin.ink.blogboot.model.common.BaseEntity;
import org.apache.ibatis.type.Alias;

/**
 * @Author: 马文澍
 * @Date: 2021/8/26 20:37
 */
@Data
@Alias("Author")
public class User extends BaseEntity {

    private String username;

    private String password;

    private String qq;

    private String mail;

    private String phone;
}
