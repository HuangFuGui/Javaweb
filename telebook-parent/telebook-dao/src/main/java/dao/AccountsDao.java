package dao;

import org.apache.ibatis.annotations.Param;

/**
 * Created by huangfugui on 2016/10/20.
 */
public interface AccountsDao {

    /**
     * 用于登录
     * @param account
     * @param password
     * @return
     */
    String selectAccount(@Param("account") String account,@Param("password") String password);

    /**
     * 在注册之前检测账号是否已经存在
     * @param account
     * @return
     */
    int selectAccountBeforeInsert(@Param("account") String account);

    /**
     * 注册账户
     * @param account
     * @param password
     * @return
     */
    int insertAccount(@Param("account") String account,@Param("password") String password);
}
