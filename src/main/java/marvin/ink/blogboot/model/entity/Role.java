package marvin.ink.blogboot.model.entity;

import marvin.ink.blogboot.model.common.BaseEntity;

/**
 * @Author: 马文澍
 * @Date: 2021/8/29 20:09
 */
public class Role extends BaseEntity {

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
