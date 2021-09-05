package marvin.ink.blogboot.service;

import marvin.ink.blogboot.vo.req.user.UserRegistryReq;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author: 马文澍
 * @Date: 2021/8/29 20:04
 */
public interface UserService extends UserDetailsService {

    void registry(UserRegistryReq userRegistryReq);
}
