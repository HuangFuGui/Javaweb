
package com.hibernate.user;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.*;

import com.demo1.bean.User;
public class UserTest {

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
	public void testSaveUser(){     //増
		//生成用户对象
		User user = new User("黄复贵咯咯哈哈","123456","1151650717@qq.com","sz",20,"13051189772","no");
		session.save(user); //保存对象进入数据库
		
	}
	
	@Test
	public void testGetUser(){    //查
		User user = (User)session.get(User.class,5);
		System.out.println(user.getHeadimg());
	}
	
	@Test
	public void testLoadUser(){    //查
		User user = (User)session.load(User.class,5);
		System.out.println(user.getEmail());
	}
	
	@Test
	public void testUpdateUser(){     //改
		User user = (User)session.get(User.class,5);
		user.setPassword("nolala");
		session.update(user);
	}
	
	@Test
	public void testDeleteUser(){     //删
		User user = (User)session.get(User.class,17);
		session.delete(user);
	}
}
