package marvin.ink.blogboot.service;

import io.swagger.models.auth.In;
import marvin.ink.blogboot.model.entity.User;
import marvin.ink.blogboot.req.user.UserSaveReq;
import marvin.ink.blogboot.res.user.UserRes;
import marvin.ink.blogboot.res.user.UserSearchRes;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * @Author: 马文澍
 * @Date: 2021/8/29 20:04
 */
public interface UserService extends UserDetailsService {

    void registry(UserSaveReq userSaveReq);

    /**
     * 修改用户基本信息
     * @param req req
     */
    void update(UserSaveReq req);

    void updateAvatar(String avatar, Integer id);

    /**
     * 登录主体
     */
    User principal();

    UserSearchRes findById(Integer id);

    List<UserSearchRes> findMyFans(Integer userId);

    List<UserSearchRes> findMyLove(Integer userId);

}
