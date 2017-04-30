package concert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by huangfugui on 2017/4/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)//在测试开始的时候自动创建Spring的应用上下文
@ContextConfiguration(classes = ConcertConfig.class)//需要在CDPlayerConfig中加载配置
public class ConcertTest {

    @Autowired
    private Peformance peformance;

    @Test
    public void testPerformance(){
        peformance.perform();
        ((Encoreable)peformance).performEncore();
    }
}
