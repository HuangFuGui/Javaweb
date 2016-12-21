package cn.muke.spring.demo1;


import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring_transaction {


	@Test
	public void testIt(){
		//申请一个IOC容器	
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//classpath:applicationContext.xml这要跟配置文件一样
				
		//通过IOC容器获得相应对象
		AccountService accountService = (AccountService) context.getBean("accountService");
		accountService.transfer("aaa", "bbb", 200d);
		        
	}
}
