package dao;

import entity.CommentInfo;
import entity.ReplyInfo;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface CommentDao {

    /**
     * 向数据库中插入评论
     * @param dynamicsId
     * @param sendId
     * @param receiveUsername
     * @param commentText
     * @return
     */
    int insertComment(@Param("dynamicsId") int dynamicsId,
                      @Param("sendId") int sendId,
                      @Param("receiveUsername") String receiveUsername,
                      @Param("commentText") String commentText,
                      @Param("commentTime") Timestamp commentTime);

    /**
     * 根据动态id查找该动态的全部评论
     * @param dynamicsId
     * @return
     */
    List<CommentInfo> selectCommentByDynamicsId(@Param("dynamicsId") int dynamicsId);

    /**
     * 根据评论id查找评论信息
     * @param commentId
     * @return
     */
    CommentInfo selectCommentById(@Param("commentId") int commentId);

    /**
     * 查找当前用户最新的评论内容（内含User对象），用于js append
     * @param sendId
     * @return
     */
    CommentInfo selectNewestCommentOfUser(@Param("sendId") int sendId);

    /**
     * 向数据库中插入评论的回复
     * @param commentId
     * @param sendId
     * @param receiveUsername
     * @param replyText
     * @param replyTime
     * @return
     */
    int insertReply(@Param("commentId") int commentId,
                    @Param("sendId") int sendId,
                    @Param("receiveUsername") String receiveUsername,
                    @Param("replyText") String replyText,
                    @Param("replyTime") Timestamp replyTime);

    /**
     * 根据用户的id查找最新回复的内容
     * @param sendId
     * @return
     */
    ReplyInfo selectReplyInfoBysendId(@Param("sendId") int sendId);

    /**
     * 根据评论id查找该评论的所有回复
     * @param commentId
     * @return
     */
    List<ReplyInfo> selectReplyInfoByCommentId(@Param("commentId") int commentId);

    /**
     * 根据回复的id查找回复信息
     * @param replyId
     * @return
     */
    ReplyInfo selectReplyInfoById(@Param("replyId") int replyId);
}
