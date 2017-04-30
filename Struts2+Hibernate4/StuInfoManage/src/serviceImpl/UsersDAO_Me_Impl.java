package serviceImpl;

import java.util.List;


/*要全导hibernate的包，不然会报错*/
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import entity.Users;   //实体类包
import service.UsersDAO_Me;    //接口包

public class UsersDAO_Me_Impl implements UsersDAO_Me  {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	@Override
	public boolean usersLogin(Users u) {
		// TODO Auto-generated method stub
				String hql = "";
				try
				{
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
					
					hql = "from Users where username =? and password =?";
					Query  query = (Query) session.createQuery(hql);
					query.setParameter(0, u.getUsername());
					query.setParameter(1, u.getPassword());
					List list = query.list();
					if(list.size()>0)
					{
						return true;
					}
					else
					{
						return false;
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
					return false;
				}
				finally
				{
					if(transaction!=null)
					{
						transaction.commit(); //提交事务
						session.close(); //关闭会话，若不释放可能导致数据库连接池溢出
						sessionFactory.close(); //关闭会话工厂	
					}
				}
	}

}
