import dtoPackage.Exposer;
import dtoPackage.SeckillExecution;
import entityPackage.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import servicePackage.SeckillService;

import java.util.List;


/**
 * Created by Administrator on 2016/6/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-Redisdao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testGetSeckillList() throws Exception {
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
        int id = 8;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}",exposer);

        //exposer=Exposer{exposed=true, md5='4f97f45ebe2797cdaef6cc643ecff6d1', seckillId=8, now=0, start=0, end=0}
    }

    @Test
    public void testExecuteSeckill() throws Exception {
        int id = 8;
        String phone = "11111111117";
        String md5 = "4f97f45ebe2797cdaef6cc643ecff6d1";

        SeckillExecution execution = seckillService.executeSeckill(id, phone, md5);

        logger.info("execution={}",execution);


        //控制台有transaction控制，说明事务启用成功

    }

    @Test
    public void testExecuteSeckillProcedure() throws Exception {
        int seckillId = 8;
        String userPhone = "11111111118";
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if(exposer.isExposed()){
            String md5 = exposer.getMd5();

            SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId, userPhone, md5);

            logger.info(execution.getStateInfo());
        }
    }
}