package cn.muke.spring.demo3;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.muke.spring.demo3.AccountService;

/*
 * Spring声明事务管理方式二：基于AspectJ的XML配置的声明
 * */
public class TestSpring_transaction3 {

	@Test
	public void TestIt(){
		//申请一个IOC容器	
		ClassPathXmlApplicationContext context3 =new ClassPathXmlApplicationContext("classpath:applicationContext3.xml");
		//classpath:applicationContext3.xml这要跟配置文件一样
								
		//通过IOC容器获得相应对象
		AccountService accountService = (AccountService) context3.getBean("accountService");
		accountService.transfer("aaa", "bbb", 200d);
	}
}
