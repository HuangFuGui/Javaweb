package cn.muke.spring.demo4;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.muke.spring.demo4.AccountService;

/*
 * Spring声明事务方式三：基于注解的事务管理方式
 * */
public class TestSpring_transaction4 {

	@Test
	public void TestIt(){
		//申请一个IOC容器	
		ClassPathXmlApplicationContext context4 =new ClassPathXmlApplicationContext("classpath:applicationContext4.xml");
		//classpath:applicationContext4.xml这要跟配置文件一样
										
		//通过IOC容器获得相应对象
		AccountService accountService = (AccountService) context4.getBean("accountService");
		accountService.transfer("aaa", "bbb", 300d);
	}
}
