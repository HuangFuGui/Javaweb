package testMyProject;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entity.Students;

public class TestSaveStudents {

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
	 * 添加测试数据
	 * */
	@Test
	public void testSaveStudents(){
		/*
		 * 在这里数据库中的主键应该是A_I，不然会报错！、
		 * 数据库中的sid是int，在实体类中可以是String，Hibernate框架会自动转换。
		 * */
		Students s1 = new Students("黄复贵~","男",new Date(),"广东啊啊");
		session.save(s1);
	}
}
