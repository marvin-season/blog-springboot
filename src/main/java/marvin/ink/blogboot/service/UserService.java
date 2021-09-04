package marvin.ink.blogboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import marvin.ink.blogboot.model.entity.User;
import marvin.ink.blogboot.vo.req.user.UserRegistryReq;
import marvin.ink.blogboot.vo.res.user.UserLoginRes;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author: 马文澍
 * @Date: 2021/8/29 20:04
 */
public interface UserService extends UserDetailsService {

    void registry(UserRegistryReq userRegistryReq);
}
