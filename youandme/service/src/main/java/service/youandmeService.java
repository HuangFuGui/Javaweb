package service;


import entity.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;

public interface youandmeService {

    /**
     * 注册
     * @param username
     * @param password
     * @param email
     * @return
     */
    int register(String username,String password,String email);

    /**
     * 登录，可能是用户名登录也可能是邮箱登录
     * @param stringToLogin
     * @param password
     * @return
     */
    User login(String stringToLogin,String password);

    /**
     * 用户上传动态，默认只能是文字+一张图片或是文字+一段视频
     * @param request
     * @param userId
     */
    void postDynamics(HttpServletRequest request,int userId);

    /**
     * 刷新进入主页时查看全部动态
     * @return
     */
    List<SocialDynamics> showDynamics();

    /**
     * 返回当前页面动态最大主键值，如果没有就是0
     * @return
     */
    int curMaxDynamicsId();

    /**
     * 从pos开始局部刷新新的动态
     * @param pos
     * @return
     */
    List<SocialDynamics> showNewDynamics(int pos);

    boolean changeHeadImg(HttpServletRequest request,int userId);

    /**
     * 用户更改个人信息，成功返回true，失败返回false
     * @param userId
     * @param username
     * @param email
     * @param address
     * @param description
     * @return
     */
    boolean changePersonalInfo(int userId,String username,String email,String address,String description);

    User queryUserById(int userId);

    /**
     * 用户点赞，返回点赞后当前动态被点赞的总数
     * @param dynamicsId
     * @param userId
     * @return
     */
    String clickLikeDynamics(int dynamicsId,int userId);

    List<Integer> showWhichLike(int userId);

    SocialDynamics showDetailDynamicsById(int dynamicsId);

    List<User> showLikeUserOfDynamics(int dynamicsId);

    /**
     * 用户对特定动态发表评论
     * @param dynamicsId
     * @param sendId
     * @param commentText
     * @return
     */
    CommentInfo sendComment(int dynamicsId,int sendId,String commentText);

    /**
     * 根据动态id查找该动态的全部评论
     * @param dynamicsId
     * @return
     */
    List<CommentInfo> showCommentById(int dynamicsId);

    /**
     * 根据评论id查找评论信息
     * @param commentId
     * @return
     */
    CommentInfo showComment(int commentId);

    /**
     * 用户发表对动态评论的回复，返回ReplyInfo对象
     * @param commentId
     * @param sendId
     * @param replyText
     */
    ReplyInfo sendReply(int commentId,int sendId,String replyText);

    /**
     * 查找评论的全部回复
     * @param commentId
     * @return
     */
    List<ReplyInfo> showAllReplyByCommentId(int commentId);

    /**
     * 用户对评论的回复进行回复
     * @param replyId
     * @param sendId
     * @param replyText
     * @return
     */
    ReplyInfo sendReplyOfReply(int replyId,int sendId,String replyText);

    ReplyInfo showReplyInfo(int replyId);

    /**
     * 首页根据用户名查找用户的Lucene搜索
     * @param inputString
     * @return 用户对象集合，其中用户名已带有高亮
     */
    List<User> luceneSearchUser(String inputString);

    void addMessage(int fromId,String fromName,int toId,String messageText,Timestamp messageDate);

    List<Message> showMessage(int fromId,int toId);

    void uploadInfo(String fileName,String uploadUsername,Timestamp uploadTime);

    List<PluploadFile> showUploadOfUser(String uploadUsername);

    /**
     * 根据id删除已经上传的文件
     * @param request
     * @param userId
     * @param id
     */
    void deletePluploadFile(HttpServletRequest request,int userId,int id);

    PluploadFile showFileOfId(int id);


}
