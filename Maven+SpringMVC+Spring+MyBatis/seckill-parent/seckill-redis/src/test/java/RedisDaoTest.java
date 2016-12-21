import daoPackage.SeckillDao;
import entityPackage.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redisPackage.RedisDao;


/**
 * Created by Administrator on 2016/6/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//∏ÊÀﬂjunit spring≈‰÷√Œƒº˛
@ContextConfiguration({"classpath:spring/spring-dao.xml"
                    ,"classpath:spring/spring-Redisdao.xml"})
public class RedisDaoTest {

    private int id = 5;
    @Autowired
    private RedisDao redisDao;

    @Autowired
    private SeckillDao seckillDao;
    @Test
    public void testSeckill() throws Exception {
        //Get and Put
        Seckill seckill = redisDao.getSeckill(id);

        if(seckill ==null){

            seckill = seckillDao.queryById(id);

            if(seckill!=null){

                String result = redisDao.putSeckill(seckill);
                System.out.println(result);

                seckill = redisDao.getSeckill(id);
                System.out.println("from redis£∫"+seckill);

            }
        }
        else
        {
            System.out.println("from redis£∫"+seckill);
        }
    }

}