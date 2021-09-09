package marvin.ink.blogboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import marvin.ink.blogboot.dao.UserMapper;
import marvin.ink.blogboot.exception.CustomizeException;
import marvin.ink.blogboot.model.entity.User;
import marvin.ink.blogboot.model.enums.ResultEnum;
import marvin.ink.blogboot.service.UserService;
import marvin.ink.blogboot.req.user.UserRegistryReq;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("username = {}", username);
        User user = baseMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
        if (ObjectUtil.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("admin, user"));
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registry(UserRegistryReq userRegistryReq) {
        User user = baseMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, userRegistryReq.getUsername()));
        Assert.isNull(user, () -> CustomizeException.is(ResultEnum.PARAM_ERROR).hint("用户名已存在"));

        userRegistryReq.setPassword(passwordEncoder.encode(userRegistryReq.getPassword()));
        user = BeanUtil.copyProperties(userRegistryReq, User.class);
        user.setLocked(false);
        baseMapper.insert(user);
        log.info("用户注册成功");
    }
}
