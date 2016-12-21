package serviceImpl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import entity.Students;
import service.StudentsDAO;

/*
 * 学生业务逻辑接口实现类
 * */
public class StudentsDAO_Impl implements StudentsDAO {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	@Override
	public List<Students> queryAllStudents() {
		// TODO Auto-generated method stub
		
		List<Students> list = null;
		String hql = "";
		
		try{
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
			
			/*
			 * 数据库中表名是students，注意这里不可以写成from students
			 * 因为HQL语句中表名是ORM映射的类名*/
			hql = "from Students";
			Query query = session.createQuery(hql);
			list = query.list();
			return list;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return list;
		}
		finally{
			transaction.commit(); //提交事务
			session.close(); //关闭会话，若不释放可能导致数据库连接池溢出
			sessionFactory.close(); //关闭会话工厂	
		}
	}

	@Override
	public Students queryStudentBysid(String sid) {
		// TODO Auto-generated method stub
	   Students s = null;     //为了严谨，开始要先定义为空！
		
		try{
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
			

			s = (Students) session.get(Students.class, sid);
			return s;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return s;
		}
		finally{
			transaction.commit(); //提交事务
			session.close(); //关闭会话，若不释放可能导致数据库连接池溢出
			sessionFactory.close(); //关闭会话工厂	
		}
	}

	@Override
	public boolean addStudents(Students s) {
		// TODO Auto-generated method stub
		try{
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
			
			//添加学生
			session.save(s);
			/*这里没有Http的session会话对象！*/
			
			return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		finally{
			transaction.commit(); //提交事务
			session.close(); //关闭会话，若不释放可能导致数据库连接池溢出
			sessionFactory.close(); //关闭会话工厂	
		}
	}

	@Override
	public boolean updateStudents(Students s) {
		// TODO Auto-generated method stub
			
			try{
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
				

				session.update(s);
				return true;
			}
			catch(Exception ex){
				ex.printStackTrace();
				return false;
			}
			finally{
				transaction.commit(); //提交事务
				session.close(); //关闭会话，若不释放可能导致数据库连接池溢出
				sessionFactory.close(); //关闭会话工厂	
			}
	}

	@Override
	public boolean deleteStudents(String sid) {
		// TODO Auto-generated method stub

		try{
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
			
			//删除学生
			Students s = (Students) session.get(Students.class, sid);
			session.delete(s);
			
			return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		finally{
			transaction.commit(); //提交事务
			session.close(); //关闭会话，若不释放可能导致数据库连接池溢出
			sessionFactory.close(); //关闭会话工厂	
		}
	}

}
