package com.Demo2.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.Demo2.bean.Grade;
import com.Demo2.bean2.Student;

public class more2one {

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
	/*
	 * （单向）多对一关联关系
	 * */
	@Test
	public void save(){
		
		Grade g = new Grade("Java三班","Java网站开发三班");
		Student stu1 = new Student("Russell.H","男");
		Student stu2 = new Student("杨千嬅咯","女");
		
		stu1.setGrade(g);    /*将学生（多）加入班级（一）中，单向*/
		stu2.setGrade(g);
		
		session.save(g);
		session.save(stu1);
		session.save(stu2);
	}
	
	@Test
	public void getGradeByStudent(){
		
		Student stu = (Student)session.get(Student.class, 32);
		System.out.println("学生姓名："+stu.getSname()+"   性别："+stu.getSex());
		
		Grade g = stu.getGrade();   //多对一关联关系
		System.out.println("所在班级："+g.getGname());
		
	}
}
