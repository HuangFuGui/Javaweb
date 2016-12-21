package servicePackage.impl;

import daoPackage.SeckillDao;
import daoPackage.SuccessKilledDao;
import dtoPackage.Exposer;
import dtoPackage.SeckillExecution;
import entityPackage.Seckill;
import entityPackage.SuccessKilled;
import enumsPackage.SeckillStateEnums;
import exceptionPackage.RepeatKillException;
import exceptionPackage.SeckillCloseException;
import exceptionPackage.SeckillException;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import redisPackage.RedisDao;
import servicePackage.SeckillService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/25.
 */
//@Service注解：将写好的SeckillService注入到SpringIOC容器中
//由于MyBatis与Spring已经有很好的整合框架，所以在spring-dao.xml中可以直接通过配置将MyBatis实现好的各dao类注入到SpringIOC容器中
@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    //注入Service依赖,自动装配,不需要手动新建相应实例
    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Autowired
    private RedisDao redisDao;
    //md5盐值字符串，用于混淆MD5
    private final String slat = "asd$%^$156120#BbK0-%^%*&!&*fef~{}@##VJ*{))&@@@@#";

    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    public Seckill getById(int seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(int seckillId) {
        //优化缓存
        //1.访问redis,降低数据库访问压力
        Seckill seckill = redisDao.getSeckill(seckillId);

        if(seckill==null){
            //2.访问数据库
            System.out.println("assess database");
            seckill = seckillDao.queryById(seckillId);
            if(seckill==null){
                return new Exposer(false,seckillId);
            }
            else {
                //3.放入redis
                System.out.println("put into redis");
                redisDao.putSeckill(seckill);
            }
        }

      /*  Seckill seckill = seckillDao.queryById(seckillId);
        if(seckill==null){
            return new Exposer(false,seckillId);
        }*/

        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        //系统当前时间
        Date nowTime = new Date();
        if(nowTime.getTime()<startTime.getTime()
                || nowTime.getTime()>endTime.getTime()){
            return new Exposer(startTime.getTime(),endTime.getTime(),nowTime.getTime(),false,seckillId);
        }
        //转化特定字符串的过程，不可逆
        String md5 = getMD5(seckillId);//
        return new Exposer(md5,true,seckillId);

    }

    //生成并返回一个md5
    private  String getMD5(int seckillId){

        String base = seckillId+"/"+slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }


    @Transactional
    /**
     * 使用注解控制事务
     */
    public SeckillExecution executeSeckill(int seckillId, String userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException {

        if(md5==null|| !md5.equals(getMD5(seckillId))){
            throw new SeckillException("seckill data rewrite");
        }
        //执行秒杀逻辑; 减库存+记录购买明细
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        try {
            int insertCount = successKilledDao.insertSuccessKilled(seckillId,userPhone,nowTime);
            //唯一：seckillId,userPhone（联合主键）
            if(insertCount<=0){
                //重复秒杀
                throw new RepeatKillException("seckill repeated");
            }
            else {
                int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
                if (updateCount <= 0) {
                    //并发量太高，有可能在等行级锁的时候库存没有了,并且秒杀时间问题在前面已经验证。
                    throw new SeckillCloseException("seckill is closed");
                }
                else {
                    //秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStateEnums.SUCCESS, successKilled);  //枚举
                }
            }
        }
        catch (SeckillCloseException e1){
            throw e1;  //抛出异常
        }
        catch (RepeatKillException e2){
            throw e2;   //抛出异常
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            //所有编译期异常转化为运行期异常    以便rollback回滚
            throw new SeckillException("seckill inner error:"+e.getMessage());    //抛出异常
        }
    }

    public SeckillExecution executeSeckillProcedure(int seckillId, String userPhone, String md5) {

        if( md5==null || !md5.equals(getMD5(seckillId)) ){
            return new SeckillExecution(seckillId,SeckillStateEnums.DATA_REWRITE);
        }

        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("seckillId",seckillId);
        map.put("phone",userPhone);
        map.put("killTime",nowTime);
        map.put("result", null);

        try{
            seckillDao.killByProcedure(map);
            int result = MapUtils.getInteger(map,"result",-2);
            if(result == 1){
                SuccessKilled sk = successKilledDao.queryByIdWithSeckill(seckillId,userPhone);
                return new SeckillExecution(seckillId,SeckillStateEnums.SUCCESS,sk);
            }
            else{
                return new SeckillExecution(seckillId,SeckillStateEnums.stateOf(result));
            }
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            return new SeckillExecution(seckillId,SeckillStateEnums.INNER_ERROR);
        }
    }
}
