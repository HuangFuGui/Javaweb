import entity.Contacts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.TelebookService;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by huangfugui on 2016/10/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
                       "classpath:spring/spring-service.xml"})
public class TelebookServiceTest {

    @Autowired
    private TelebookService telebookService;

    @Test
    public void showAllContacts() throws Exception {
        List<Contacts> contactsList = telebookService.showAllContacts(2);
        if (contactsList!=null){
            System.out.println("电话簿中一共有"+contactsList.size()+"个联系人：");
            for(Contacts contacts:contactsList){
                System.out.println(contacts);
            }
        }
        else{
            System.out.println("电话簿中还没有联系人！");
        }
    }

    @Test
    public void login() throws Exception {
        int result = telebookService.login("admin","123456");
        if(result>0){
            System.out.println("登陆成功！");
        }
        else if(result==-1){
            System.out.println("账号或密码错误！");
        }
    }

    @Test
    public void register() throws Exception {
        int result = telebookService.register("我是黄复贵","123");
        if(result==-1){
            System.out.println("账号已存在！请重新输入！");
        }
        else if(result==0){
            System.out.print("注册失败！");
        }
        else{
            System.out.println("注册成功！");
        }
    }
}