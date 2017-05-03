package org.huangfugui.ibatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.huangfugui.ibatis.enums.UserType;
import org.huangfugui.ibatis.po.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huangfugui on 2017/4/27.
 */
@Repository
public interface UserMapper {

    /**
     * 用户注册
     * @param username
     * @param password
     * @param userType
     * @return
     */
    int insertUser(@Param("username") String username, @Param("password") String password, @Param("userType") UserType userType);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 更新用户昵称
     * @param id
     * @param nickname
     * @return
     */
    int updateNicknameById(@Param("id") int id,@Param("nickname") String nickname);

    /**
     * 更新用户签名
     * @param id
     * @param sign
     * @return
     */
    int updateSignById(@Param("id") int id,@Param("sign") String sign);
}
