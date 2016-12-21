package com.Demo2.test;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.*;

import com.Demo2.bean.Grade;
/*
 * 单向一对多关联关系（班级--->学生）
 * 建立关联关系后，可以方便的从一个对象导航到另一个对象
 * 注意关联方向
 * */
import com.Demo2.bean2.Student;

public class testIt {
	
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
	public void testSaveUser(){     
		//如果希望在学生表中添加对应的班级编号，需要在班级中添加学生，建立关联关系
		Grade g = new Grade("Java一班","2014级Java软件开发一班");
		Student stu1 = new Student("黄复贵","男");
		Student stu2 = new Student("杨千嬅","女");
		g.getStudents().add(stu1);
		g.getStudents().add(stu2);		

		session.save(g);
		session.save(stu1);
		session.save(stu2);
		
	}
	
	@Test
	public void findStudentsByGrade(){
		/*通过班号去找该班所有学生，一对多映射关系*/
		Grade g = (Grade)session.get(Grade.class, 10);
		System.out.println("班级："+g.getGname()+" "+"班级详细信息："+g.getGdesc());
		Set<Student> students = g.getStudents();
		for(Student stu:students){
			System.out.println("姓名："+stu.getSname()+" 性别："+stu.getSex());
		}
	}
	
	@Test
	public void updateStudentsInfor(){
		
		Grade g= new Grade("Java二班", "Java网站开发二班");
		Student stu = (Student)session.get(Student.class, 17);
		g.getStudents().add(stu);    /*原来在一班的学生现在添加到二班中去，保存Grade g后Student中相应信息就会更改*/
		session.save(g);
		
	}
	
	@Test
	public void deleteStudents(){
        /*删除时不需要关联更方便*/
		Student stu = (Student)session.get(Student.class, 18);
		session.delete(stu);
	}


	
}
