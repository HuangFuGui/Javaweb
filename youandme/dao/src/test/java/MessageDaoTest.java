import dao.MessageDao;
import entity.Message;
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
public class MessageDaoTest {

    @Autowired
    private MessageDao messageDao;
    @Test
    public void testInsertMessage() throws Exception {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        messageDao.insertMessage(1,"asd",4,"阿斯达斯的撒",now);
    }

    @Test
    public void testSelectMessageOfTwo() throws Exception {
        List<Message> list = messageDao.selectMessageOfTwo(123,118);
        for(Message message:list){
            System.out.println(message);
        }
    }
}