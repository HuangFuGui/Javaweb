
import daoPackage.SuccessKilledDao;
import entityPackage.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;


/**
 * Created by Administrator on 2016/6/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//∏ÊÀﬂjunit spring≈‰÷√Œƒº˛
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void testInsertSuccessKilled() throws Exception {
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        int insertCount = successKilledDao.insertSuccessKilled(6, "10051188772",nowTime);
        System.out.println("insertCount="+insertCount);
    }

    @Test
    public void testQueryByIdWithSeckill() throws Exception {
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(6, "13051188772");
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
}