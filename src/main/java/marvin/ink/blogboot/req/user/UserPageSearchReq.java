package marvin.ink.blogboot.req.user;

import lombok.Data;
import marvin.ink.blogboot.model.common.PageRequest;

@Data
public class UserPageSearchReq extends PageRequest {
    private String username;

    private String roleName;

    private String qq;

    private String mail;

    private String phone;

    private String wx;
}
