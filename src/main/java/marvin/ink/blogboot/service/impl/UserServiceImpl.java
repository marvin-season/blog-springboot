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
import marvin.ink.blogboot.req.user.UserSaveReq;
import marvin.ink.blogboot.res.user.UserRes;
import marvin.ink.blogboot.res.user.UserSearchRes;
import marvin.ink.blogboot.service.UserService;
import marvin.ink.blogboot.utils.SecurityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    public void registry(UserSaveReq userSaveReq) {
        User user = baseMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, userSaveReq.getUsername()));
        Assert.isNull(user, () -> CustomizeException.is(ResultEnum.PARAM_ERROR).hint("用户名已存在"));

        userSaveReq.setPassword(passwordEncoder.encode(userSaveReq.getPassword()));
        user = BeanUtil.copyProperties(userSaveReq, User.class);
        user.setLocked(false);

        if (ObjectUtil.isNull(userSaveReq.getAvatar())) {
            // 默认头像
            user.setAvatar("https://marvin-blog.oss-cn-hangzhou.aliyuncs.com/20210921/49a62dd5b1154de58471268c72145d1b.jpg");
        }
        baseMapper.insert(user);
        log.info("用户注册成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserSaveReq req) {
        User bean = baseMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId, req.getId()));
        Assert.notNull(bean, () -> CustomizeException.is(ResultEnum.PARAM_ERROR).hint("用户不存在"));

        User user = BeanUtil.copyProperties(req, User.class);
        baseMapper.updateById(user);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAvatar(String avatar, Integer id) {
        User bean = baseMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId, id));
        Assert.notNull(bean, () -> CustomizeException.is(ResultEnum.PARAM_ERROR).hint("用户不存在"));

        bean.setAvatar(avatar);
        baseMapper.updateById(bean);
    }

    @Override
    public User principal() {
        Integer id = SecurityUtils.getUser().getId();
        if (ObjectUtil.isNull(id)) {
            throw CustomizeException.is(ResultEnum.PARAM_ERROR).hint("数据异常");
        }
        User user = baseMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId, id));
        user.setPassword(null);
        return user;
    }

    @Override
    public UserSearchRes findById(Integer id) {

        User user = baseMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId, id));
        user.setPassword(null);
        return BeanUtil.copyProperties(user, UserSearchRes.class);
    }

    @Override
    public List<UserSearchRes> findMyLove(Integer userId) {
        List<Integer> loveIds = baseMapper.findMyLove(userId);
        if (ObjectUtil.isEmpty(loveIds)) {
            return null;
        }
        List<User> users = baseMapper.selectList(Wrappers.<User>lambdaQuery()
                .in(User::getId, loveIds));

        return BeanUtil.copyToList(users, UserSearchRes.class);
    }

    @Override
    public List<UserSearchRes> findMyFans(Integer userId) {
        List<Integer> fansIds = baseMapper.findMyFans(userId);
        if (ObjectUtil.isEmpty(fansIds)) {
            return null;
        }
        List<User> users = baseMapper.selectList(Wrappers.<User>lambdaQuery()
                .in(User::getId, fansIds));

        return BeanUtil.copyToList(users, UserSearchRes.class);
    }
}
