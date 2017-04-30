package testMyProject;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import service.StudentsDAO;
import serviceImpl.StudentsDAO_Impl;

import entity.Students;

public class TestStudentsDAO_Impl {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void init(){
		//创建配置对象
		Configuration config = new Configuration().configure();
		//创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//创建会话工厂对象
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		//会话对象
		session = sessionFactory.openSession();
		//开启事务
		transaction = session.beginTransaction();
	}
	
	
	@After
	public void destory(){
		transaction.commit(); //提交事务
		session.close(); //关闭会话，若不释放可能导致数据库连接池溢出
		sessionFactory.close(); //关闭会话工厂	
	}
	
	@Test
	public void testQueryAllStudents(){
		
		/*
		 * 注意这里的写法！
		 * */
		 StudentsDAO sdao = new StudentsDAO_Impl();      //sdao为接口！
		 List<Students> list = sdao.queryAllStudents();     
		 
		 for(int i=0;i<list.size();i++)
		 {
			 System.out.println("学生姓名："+list.get(i).getSname());
		 }
	}
}
