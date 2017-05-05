package main;

import mapper.StudentMapper;
import org.apache.ibatis.session.SqlSession;
import po.StudentBean;
import util.SqlSessionFactoryUtil;

/**
 * Created by huangfugui on 2017/4/24.
 */
public class testAssociationAndCollection {
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            StudentBean student = studentMapper.getStudent(1L);
            System.out.println(student);

            sqlSession.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            sqlSession.rollback();//SQL执行中间出错会回滚
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
