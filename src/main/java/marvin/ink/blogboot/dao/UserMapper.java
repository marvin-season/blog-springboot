package marvin.ink.blogboot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import marvin.ink.blogboot.model.entity.User;
import marvin.ink.blogboot.res.user.UserRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 马文澍
 * @Date: 2021/8/29 20:00
 */
public interface UserMapper extends BaseMapper<User> {
    List<Integer> findMyFans(@Param("userId") Integer userId);
    List<Integer> findMyLove(@Param("userId") Integer userId);
}
