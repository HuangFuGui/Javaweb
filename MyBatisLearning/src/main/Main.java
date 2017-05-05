package main;

import mapper.RoleMapper;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.session.SqlSession;
import po.Role;
import util.SqlSessionFactoryUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangfugui on 2017/4/5.
 */
public class Main {
    public static void main(String[] args){
        SqlSession sqlSession = null;
        SqlSession sqlSession2 = null;
        try{
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            /*Role role = new Role();
            role.setNote("learning Mybatis");
            role.setRoleName("huangfugui");
            //在此之前会Setting autocommit to false on JDBC Connection
            roleMapper.insertRole(role);
            System.out.println("插入后的id：" + role.getId());//MyBatis神奇的回填role对象的id字段*/
            /*roleMapper.deleteRole(14L);*/

            /*Map<String,String> paramsMap = new HashMap<String,String>();
            paramsMap.put("roleName","huang");
            paramsMap.put("note","Mybatis");
            List<Role> list2 = roleMapper.findRoleByMap(paramsMap);
            //MyBatis默认支持一级缓存，相同SqlSession相同SQL相同参数的查询将不再发送SQL到数据库
            List<Role> list = roleMapper.findRoleByMap("huang","Mybatis");
            for(Role role:list){
                System.out.println(role);
            }
            sqlSession.commit();//要手动提交，否则一定会回滚

            sqlSession2 = SqlSessionFactoryUtil.openSqlSession();
            RoleMapper roleMapper2 = sqlSession.getMapper(RoleMapper.class);
            List<Role> list3 = roleMapper.findRoleByMap("huang","Mybatis");
            for(Role role:list3){
                System.out.println(role);
            }
            sqlSession2.commit();*/
            //List<Role> list = roleMapper.findRoles("fu");
            List<Role> list = roleMapper.findRoles(null);
            for(Role role:list){
                System.out.println(role);
            }
            sqlSession.commit();
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            sqlSession.rollback();//SQL执行中间出错会回滚
        }
        finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }
}
