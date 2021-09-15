package marvin.ink.blogboot.utils;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import marvin.ink.blogboot.model.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author: 马文澍
 * @Date: 2021/9/14 20:49
 * Description: SecurityUtils
 */
@Slf4j
public class SecurityUtils {

    public static User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return BeanUtil.copyProperties(authentication.getPrincipal(), User.class);
    }
}
