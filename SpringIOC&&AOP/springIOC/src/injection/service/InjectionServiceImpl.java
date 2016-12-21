package injection.service;

import injection.dao.InjectionDAO;

public class InjectionServiceImpl implements InjectionService {


	private InjectionDAO injectionDAO;  //定义一个成员变量
	
	//设值注入
	public void setInjectionDAO(InjectionDAO injectionDAO) {
		this.injectionDAO = injectionDAO;
	}
	
	//构造器注入
	/*public InjectionServiceImpl(InjectionDAO injectionDAO) {
		super();
		this.injectionDAO = injectionDAO;
	}*/

	public void start(){
		System.out.println("Bean starts now!");
	}
	public void end(){
		System.out.println("Bean ends now!");
	}

	@Override
	public void save(String args) {
		//模拟业务逻辑操作
		System.out.println("Service接收参数："+args);
		args = args+"的hashCode:"+this.hashCode();
		injectionDAO.save(args);  //IOC容器在程序需要时自动采用注入创建了该对象
	}

}
