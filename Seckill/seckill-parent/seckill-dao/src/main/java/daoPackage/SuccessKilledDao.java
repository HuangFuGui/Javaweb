package daoPackage;


import entityPackage.SuccessKilled;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2016/5/23.
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细，联合主键可过滤重复
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccessKilled(@Param("seckillId") int seckillId, @Param("userPhone") String userPhone, @Param("createTime") Timestamp createTime);

    /**
     * 根据id查询SuccessKilled并携带秒杀产品对象实体
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") int seckillId, @Param("userPhone") String userPhone);
}
