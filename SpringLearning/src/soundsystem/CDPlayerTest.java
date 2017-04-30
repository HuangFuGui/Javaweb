package soundsystem;

import concert.Audience;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by huangfugui on 2017/3/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)//在测试开始的时候自动创建Spring的应用上下文
@ContextConfiguration(classes = CDPlayerConfig.class)//需要在CDPlayerConfig中加载配置
//@ContextConfiguration({"classpath:spring.xml"})
public class CDPlayerTest {

    @Autowired
    private CompactDisc compactDisc;

    @Autowired
    private MediaPlayer mediaPlayer;

    @Test
    public void cdShouldNotBeNull(){
        compactDisc.play();
    }

    @Test
    public void mpShouldNotBeNull(){
        mediaPlayer.play();
    }

}
