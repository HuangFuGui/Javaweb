package dao;

import entity.User;
import org.apache.ibatis.annotations.Param;

public interface LoginDao {
    /**
     * 插入用户，即注册帐号
     * @param username
     * @param password
     * @return
     */
    int insertUser(@Param("username") String username,
                   @Param("password") String password,
                   @Param("email") String email,
                   @Param("joinTime") String joinTime);


    /**
     * 登录，查找全部User是否有相应用户名或邮箱
     * 要求用户名与邮箱必须是唯一的，且用户名与邮箱也不一样。
     * 正常情况下只返回一个User
     * @param stringToLogin
     * @param password
     * @return
     */
    User selectUserFromAllUser(@Param("stringToLogin") String stringToLogin,@Param("password") String password);
}
