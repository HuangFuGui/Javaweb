package cn.muke.spring.demo2;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.muke.spring.demo2.AccountService;

/*
 * Spring声明式事务管理方式一的测试类
 * */
public class TestSpring_transaction2 {

	@Test
	public void testIt(){
		
		
		//申请一个IOC容器	
		ClassPathXmlApplicationContext context2=new ClassPathXmlApplicationContext("classpath:applicationContext2.xml");
		//classpath:applicationContext.xml这要跟配置文件一样
						
		//通过IOC容器获得相应对象
		//注入代理类accountServiceProxy：因为代理类进行了增强
		AccountService accountService = (AccountService) context2.getBean("accountServiceProxy");
		accountService.transfer("aaa", "bbb", 200d);
	}
}
