package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/5/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testGetSeckillList() throws Exception {
        // Closing non transactional SqlSession    因为为只读，所以不是在事务控制下！
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}",list);
    }

    @Test
    public void testGetById() throws Exception {
        Seckill seckill = seckillService.getById(5);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void testExportSeckillUrl() throws Exception {
        int id = 7;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}",exposer);

        //输出：exposer=Exposer{exposed=true, md5='791ced66f7f47df119f2facd98f330a1', seckillId=5, now=0, start=0, end=0}
    }

    @Test
    public void testExecuteSeckill() throws Exception {

        int id = 6;
        String phone = "13051189778";
        String md5 = "791ced66f7f47df119f2facd98f330a1";
        SeckillExecution execution = seckillService.executeSeckill(id, phone, md5);
        logger.info("execution={}",execution);
        //报错：org.seckill.exception.SeckillException: seckill data rewrite

        //修改后：
        /**
         * Registering transaction synchronization for SqlSession
         * JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@47dd778] will be managed by Spring
         */
    }
}