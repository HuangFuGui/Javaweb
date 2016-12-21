package injection.test;

import injection.service.InjectionService;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import schema.advice.biz.AspectBiz;

public class testsave {

	@Test
	public void testIt(){
		
		//申请一个IOC容器	
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//classpath:applicationContext.xml这要跟配置文件一样
		
		//通过IOC容器获得相应对象
        InjectionService injectionService = (InjectionService) context.getBean("injectionService");
        injectionService.save("ppppp");
        
        InjectionService injectionService1 = (InjectionService) context.getBean("injectionService");
        injectionService1.save("ppppp");
  
        context.close();//关闭IOC容器
        context.destroy();
	}
	
	@Test
	public void testBiz(){
		//申请一个IOC容器	
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//classpath:applicationContext.xml这要跟配置文件一样
				
		//通过IOC容器获得相应对象
		AspectBiz biz = (AspectBiz) context.getBean("aspectBiz");
		biz.biz();
		/*输出：
		Bean starts now!
		This is before
		AspectBiz.biz
		Bean ends now!
		*/
		context.close();
	}
}
