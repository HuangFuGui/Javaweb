package dao;

import entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    /**
     * 根据用户唯一标识更新头像
     * @param userId
     * @return
     */
    int updateUserHeadImg( @Param("userId") int userId,
                           @Param("headImg") String headImg);

    /**
     * 根据用户id查找用户全部信息，用于更新session
     * @param userId
     * @return
     */
    User selectUserById(@Param("userId") int userId);


    /**
     * 用户更新用户名，邮箱等文本信息
     * @param userId
     * @param username
     * @param email
     * @param address
     * @param description
     * @return
     */
    int updateUser(@Param("userId") int userId,
                   @Param("username") String username,
                   @Param("email") String email,
                   @Param("address") String address,
                   @Param("description") String description);

    /**
     * 用户每发表一次动态，其动态数量就+1
     * @param userId
     * @return
     */
    int updateDynamicsNum(@Param("userId") int userId);

    List<User> selectAllUserForLucene();
}
