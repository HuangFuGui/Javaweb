import entity.User;
import dao.LoginDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class LoginDaoTest {

    @Autowired
    private LoginDao loginDao;
    @Test
    public void testInsertUser() throws Exception {

        String username = "Hill";
        String password = "123";
        String email = "1151650717@qq.com";
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataString = simpleDateFormat.format(date);

        int insertResult = loginDao.insertUser(username,password,email,dataString);
        System.out.println(insertResult);
    }

    @Test
    public void testSelectUserFromAllUser() throws Exception {
        String stringToLogin = "1151650717@qq.com";
        String password = "123456789";
        User user = loginDao.selectUserFromAllUser(stringToLogin,password);
        System.out.println(user);
        //若登录不成功，返回为null
        //若登录成功，返回数据库中全部字段
    }
}