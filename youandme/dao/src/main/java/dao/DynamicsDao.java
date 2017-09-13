package dao;

import entity.SocialDynamics;
import entity.User;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface DynamicsDao {

    /**
     * 将动态插入数据库
     * @param userId
     * @param dynamicsText
     * @param dynamicsFile
     * @return
     */
    int insertDynamics(@Param("userId") int userId,
                       @Param("dynamicsText") String dynamicsText,
                       @Param("dynamicsFile") String dynamicsFile,
                       @Param("createTime") Timestamp createTime);


    /**
     * 主页查找数据库表中全部个人动态，不显示点赞用户头像，不显示评论等
     * @return
     */
    List<SocialDynamics> selectAllDynamics();

    /**
     * 查找当前页面动态主键的最大值
     * @return
     */
    String selectMaxDynamicsId();

    /**
     * 查找比参数dynamicsId大的动态，，不显示点赞用户头像，不显示评论等，用户ajax页面部分刷新
     * @param dynamicsId
     * @return
     */
    List<SocialDynamics> selectDynamicsFromPos(@Param("dynamicsId") int dynamicsId);


    /**
     * 点赞操作，动态表中like_num +1
     * @param dynamicsId
     * @return
     */
    int updateLikeNum(@Param("dynamicsId") int dynamicsId);

    /**
     * 取消点赞操作，动态表中like_num -1
     * @param dynamicsId
     * @return
     */
    int updateLikeNumSub(@Param("dynamicsId") int dynamicsId);

    /**
     * 点赞操作，保存用户id，点赞的动态id
     * @param dynamicsId
     * @param userId
     * @return
     */
    int insertLike(@Param("dynamicsId") int dynamicsId,@Param("userId") int userId);

    /**
     * 取消点赞操作，删除用户点赞过的动态的记录
     * @param dynamicsId
     * @param userId
     * @return
     */
    int deleteLike(@Param("dynamicsId") int dynamicsId,@Param("userId") int userId);

    /**
     * 查看是否用户有点赞操作，如果有，说明重复点赞，即取消点赞，如果没有，则是第一次点赞，是点赞操作。
     * @param dynamicsId
     * @param userId
     * @return
     */
    int selectLike(@Param("dynamicsId") int dynamicsId,@Param("userId") int userId);

    /**
     * 查看当前动态的点赞数
     * @param dynamicsId
     * @return
     */
    int selectLikeNum(@Param("dynamicsId") int dynamicsId);

    /**
     * 查看当前用户点赞了哪些动态，用户进入主页时显示动态点赞红心。
     * @param userId
     * @return
     */
    List<Integer> selectWhichLike(@Param("userId") int userId);

    /**
     * 点击评论图标后根据id显示动态详细信息
     * @param dynamicsId
     * @return
     */
    SocialDynamics selectDetailDynamicsById(@Param("dynamicsId") int dynamicsId);

    /**
     * 查找指定动态的点赞用户的id与头像
     * @param dynamicsId
     * @return
     */
    List<User> selectLikeUserOfDynamics(@Param("dynamicsId") int dynamicsId);
}
