package org.seckill.dao.cache;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.dto.Exposer;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/6/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//∏ÊÀﬂjunit spring≈‰÷√Œƒº˛
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {

    private int id = 7;
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