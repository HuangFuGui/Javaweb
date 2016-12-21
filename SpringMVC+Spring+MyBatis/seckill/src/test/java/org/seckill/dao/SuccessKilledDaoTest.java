package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/5/24.
 */
/*
*配置spring与junit的整合，junit启动时加载springIOC容器
* */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;
    @Test
    public void testInsertSuccessKilled() throws Exception {

        /*
        * 第一次：insertCount=1;
        * 第二次：insertCount=0;
        * 所以联合主键不允许同一个用户重复秒杀！
        * */
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        int insertCount = successKilledDao.insertSuccessKilled(6, "13051189772",nowTime);
        System.out.println("insertCount="+insertCount);
    }

    @Test
    public void testQueryByIdWithSeckill() throws Exception {
        /*
        * 19:15:12.559 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@4fdfa676] will not be managed by Spring
19:15:12.568 [main] DEBUG o.s.d.S.queryByIdWithSeckill - ==>  Preparing:
SELECT
    sk.seckill_id,
    sk.user_phone,
    sk.create_time,
    sk.state,
    s.seckill_id "seckill.seckill_id",
    s.name "seckill.name",
    s.number "seckill.number",
    s.start_time "seckill.start_time",
    s.end_time "seckill.end_time",
    s.create_time "seckill.create_time"
FROM
    success_killed sk INNER JOIN seckill s ON sk.seckill_id = s.seckill_id
WHERE
    sk.seckill_id = ? AND sk.user_phone=?

19:15:12.682 [main] DEBUG o.s.d.S.queryByIdWithSeckill - ==> Parameters: 6(Integer), 13051189772(String)
19:15:12.742 [main] DEBUG o.s.d.S.queryByIdWithSeckill - <==      Total: 1
19:15:12.748 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@620aa4ea]

SuccessKilled
{seckillId=6, userPhone='13051189772', state=0, createTime=null}

Seckill
{seckillId=6, name='500元秒杀ipad2', number=200, startTime=Sun Nov 01 00:00:00 GMT+08:00 2015, endTime=Mon Nov 02 00:00:00 GMT+08:00 2015, createTime=null}

        * */
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(6, "13051189772");
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
}