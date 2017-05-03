import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.huangfugui.ibatis.enums.UserType;
import org.huangfugui.ibatis.mapper.UserMapper;
import org.huangfugui.ibatis.po.User;
import org.huangfugui.ibatis.util.SqlSessionFactoryUtil;
import org.huangfugui.spring.MyConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by huangfugui on 2017/4/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyConfig.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insertUser() throws Exception {

        /*SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int result = userMapper.insertUser("黄复贵","qwe123");
        System.out.println(result);
        sqlSession.commit();*/

        int result = userMapper.insertUser("1151650717@qq.com","123", UserType.USER);
        System.out.println(result);
    }

    @Test
    public void selectByUsernameAndPassword() throws Exception {

        /*SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByUsernameAndPassword("黄复贵","qwe123");
        System.out.println(user);
        sqlSession.commit();*/

        User user = userMapper.selectByUsernameAndPassword("1151650717@qq.com","123");
        System.out.println(user);
    }
}