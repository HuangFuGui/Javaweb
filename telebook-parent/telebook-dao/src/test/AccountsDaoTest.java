import dao.AccountsDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by huangfugui on 2016/10/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class AccountsDaoTest {

    @Autowired
    private AccountsDao accountsDao;

    @Test
    public void selectAccountBeforeInsert() throws Exception {
        int result = accountsDao.selectAccountBeforeInsert("admin2");
        if(result==1){
            System.out.println("账号已被注册存在，请重新输入！");
        }
        else if(result==0){
            System.out.println("可以注册！");
        }
    }

    @Test
    public void insertAccount() throws Exception {
        int result = accountsDao.insertAccount("哈哈","123");
        if(result==1){
            System.out.println("注册成功！");
        }
        else if(result==0){
            System.out.println("账号已存在！注册失败！");
        }
    }

}