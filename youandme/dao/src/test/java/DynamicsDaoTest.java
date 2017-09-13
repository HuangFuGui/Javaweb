import dao.DynamicsDao;
import entity.SocialDynamics;
import entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class DynamicsDaoTest {

    @Autowired
    private DynamicsDao dynamicsDao;
    @Test
    public void testInsertDynamics() throws Exception {
        int userId = 116;
        String dynamicsText = "½ñÌì´òÇòÀ²£¡¹þ¹þ¹þ¹þ";
        String dynamicsFile = "D:\\file";
        Timestamp now = new Timestamp(System.currentTimeMillis());
        int insertDynamicsResult = dynamicsDao.insertDynamics(userId,dynamicsText,dynamicsFile,now);
        System.out.println(insertDynamicsResult);
    }

    @Test
    public void testSelectAllDynamics() throws Exception {

        List<SocialDynamics> list = dynamicsDao.selectAllDynamics();
        for(SocialDynamics socialDynamics:list){
            System.out.println(socialDynamics);
            System.out.println(socialDynamics.getUser());
        }
    }

    @Test
    public void testSelectMaxDynamicsId() throws Exception {
        System.out.println(dynamicsDao.selectMaxDynamicsId());
    }

    @Test
    public void testSelectDynamicsFromPos() throws Exception {
        List<SocialDynamics> list = dynamicsDao.selectDynamicsFromPos(15);
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }

    @Test
    public void testUpdateLikeNum() throws Exception {
        int updateResult = dynamicsDao.updateLikeNum(39);
        System.out.println(updateResult);
    }

    @Test
    public void testInsertLike() throws Exception {
        int insertResult = dynamicsDao.insertLike(39,128);
        System.out.println(insertResult);
    }

    @Test
    public void testSelectLikeNum() throws Exception {
        int selectResult = dynamicsDao.selectLikeNum(39);
        System.out.println(selectResult);
    }

    @Test
    public void testSelectWhichLike() throws Exception {
        List<Integer> list = dynamicsDao.selectWhichLike(122);
        for(Integer dynamicsId:list){
            System.out.println(dynamicsId);
        }
    }

    @Test
    public void testSelectDetailDynamicsById() throws Exception {
        SocialDynamics socialDynamics = dynamicsDao.selectDetailDynamicsById(40);
        System.out.println(socialDynamics);
    }

    @Test
    public void testSelectLikeUserOfDynamics() throws Exception {
        List<User> list = dynamicsDao.selectLikeUserOfDynamics(40);
        for(User user:list){
            System.out.println(user);
        }
    }
}