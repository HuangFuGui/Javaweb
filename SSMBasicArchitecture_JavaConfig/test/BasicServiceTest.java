import org.huangfugui.dto.Result;
import org.huangfugui.ibatis.po.User;
import org.huangfugui.spring.MyConfig;
import org.huangfugui.spring.service.BasicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by huangfugui on 2017/4/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyConfig.class)
public class BasicServiceTest {

    @Autowired
    private BasicService basicService;

    @Test
    public void registerUser() throws Exception {
        Result result = basicService.registerUser("1151650717@qq.com","123","0");
        System.out.println(result);
    }
}