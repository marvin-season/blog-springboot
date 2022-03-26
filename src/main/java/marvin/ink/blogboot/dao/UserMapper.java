package marvin.ink.blogboot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import marvin.ink.blogboot.model.entity.Role;
import marvin.ink.blogboot.model.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 马文澍
 * @Date: 2021/8/29 20:00
 */
public interface UserMapper extends BaseMapper<User> {
    List<Integer> findMyFans(@Param("userId") Integer userId);
    List<Integer> findMyLove(@Param("userId") Integer userId);

    void addLove(Integer whoId,@Param("fansId") Integer fansId);

    void deleteLove(Integer whoId,@Param("fansId") Integer fansId);

    Role findRoleByUserId(Integer id);

    List<User> findUserByRoleName(@Param("name") String roleName);
}
