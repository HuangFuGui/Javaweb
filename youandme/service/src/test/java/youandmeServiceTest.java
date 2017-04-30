import entity.SocialDynamics;
import entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.youandmeService;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/7/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
                       "classpath:spring/spring-service.xml"})
public class youandmeServiceTest {

    @Autowired
    private youandmeService youandmeService;
    @Test
    public void testRegister() throws Exception {
        String username = "haha";
        String password = "123456789";
        String email = "1151650717@qq.com";
        youandmeService.register(username,password,email);
    }

    @Test
    public void testLogin() throws Exception {
        String stringToLogin = "1151650717@qq.com";
        String password = "12345678";
        User user = youandmeService.login(stringToLogin,password);
        System.out.println(user);
    }

    @Test
    public void testShowDynamics() throws Exception {
        List<SocialDynamics> list = youandmeService.showDynamics();
        for(SocialDynamics socialDynamics:list){
            System.out.println(socialDynamics);
        }
    }

    @Test
    public void testLuceneSearchUser() throws Exception {
        List<User> list = youandmeService.luceneSearchUser("»Æ¸´¹ó");
        for(User user:list){
            System.out.println(user);
        }
    }

}