package org.huangfugui.ibatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.huangfugui.ibatis.po.User;
import org.springframework.stereotype.Repository;

/**
 * Created by huangfugui on 2017/4/27.
 */
@Repository
public interface UserMapper {

    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     */
    int insertUser(@Param("username") String username,@Param("password") String password);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    User selectByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
}
