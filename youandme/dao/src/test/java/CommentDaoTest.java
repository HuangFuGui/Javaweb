import dao.CommentDao;
import entity.CommentInfo;
import entity.ReplyInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class CommentDaoTest {

    @Autowired
    private CommentDao commentDao;
    @Test
    public void testInsertComment() throws Exception {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        int insertCommentResult = commentDao.insertComment(40,122,"Hill","¹þ¹þÎûÎûÎûÎûoooooooooo",now);
        System.out.println(insertCommentResult);
    }

    @Test
    public void testSelectCommentByDynamicsId() throws Exception {
        List<CommentInfo> list = commentDao.selectCommentByDynamicsId(40);
        for(CommentInfo commentInfo:list){
            System.out.println(commentInfo);
        }
    }

    @Test
    public void testSelectNewestCommentOfUser() throws Exception {
        CommentInfo commentInfo = commentDao.selectNewestCommentOfUser(122);
        System.out.println(commentInfo);
    }

    @Test
    public void testSelectNewestCommentOfUser1() throws Exception {
        CommentInfo commentInfo = commentDao.selectCommentById(2);
        System.out.println(commentInfo);
    }

    @Test
    public void testInsertReply() throws Exception {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        int insertReplyResult = commentDao.insertReply(3,123,"¹þ¹þ","»Ø¸´À²À²À²À²",now);
        System.out.println(insertReplyResult);
    }

    @Test
    public void testSelectReplyInfoBysendId() throws Exception {
        ReplyInfo replyInfo = commentDao.selectReplyInfoBysendId(123);
        System.out.println(replyInfo);
    }

    @Test
    public void testSelectReplyInfoByCommentId() throws Exception {
        List<ReplyInfo> list = commentDao.selectReplyInfoByCommentId(4);
        for(ReplyInfo replyInfo:list){
            System.out.println(replyInfo);
        }
    }

    @Test
    public void testSelectReplyInfoById() throws Exception {
        ReplyInfo replyInfo = commentDao.selectReplyInfoById(5);
        System.out.println(replyInfo);
    }
}