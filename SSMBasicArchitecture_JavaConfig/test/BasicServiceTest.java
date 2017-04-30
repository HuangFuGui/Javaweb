import org.huangfugui.ibatis.po.User;
import org.huangfugui.spring.MyConfig;
import org.huangfugui.spring.service.BasicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by huangfugui on 2017/4/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyConfig.class)
public class BasicServiceTest {

    @Autowired
    private BasicService basicService;

    @Test
    public void registerUser() throws Exception {
        boolean result = basicService.registerUser("geek_huangfugui@163.com","123qwe");
        if(result==true){
            System.out.println("注册成功");
        }
        else{
            System.out.println("注册失败");
        }
    }

    @Test
    public void login() throws Exception {
        String username = "geek_huangfugui@163.com";
        String password = "123qwe";
        User user = basicService.login(username,password);
        System.out.println(user);
    }

}