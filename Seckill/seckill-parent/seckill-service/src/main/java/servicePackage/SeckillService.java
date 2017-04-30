package servicePackage;


import dtoPackage.Exposer;
import dtoPackage.SeckillExecution;
import entityPackage.Seckill;
import exceptionPackage.RepeatKillException;
import exceptionPackage.SeckillCloseException;
import exceptionPackage.SeckillException;

import java.util.List;

/**业务接口：站在“使用者”角度设计接口
 * 三个方面：方法定义粒度，参数，返回类型（return 类型/异常）
 * Created by Administrator on 2016/5/24.
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(int seckillId);

    /**
     *秒杀开启时输出秒杀接口地址
     * 否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(int seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(int seckillId, String userPhone, String md5)
        throws SeckillException, RepeatKillException, SeckillCloseException;

    /**
     * 执行秒杀操作By存储过程
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckillProcedure(int seckillId, String userPhone, String md5);
}
