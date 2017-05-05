package main;

import enums.Sex;
import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import po.User;
import util.SqlSessionFactoryUtil;

import java.util.Date;

/**
 * Created by huangfugui on 2017/4/10.
 */
public class testEnumOrdinalTypeHandler {
    public static void main(String[] args){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setUserName("zhangsan");
            user.setCnname("黄复贵");
            user.setMobile("13051189772");
            user.setSex(Sex.MALE);
            user.setEmail("geek_huangfugui@163.com");
            user.setNote("I am huangfugui");
            user.setBirthday(new Date());
            userMapper.insertUser(user);
            User user1 = userMapper.getUser(18L);
            System.out.println(user1);
            sqlSession.commit();
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            sqlSession.rollback();
        }
        finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }
}
