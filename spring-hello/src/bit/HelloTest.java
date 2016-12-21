package bit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloTest {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloApi helloApi = context.getBean("hello",HelloApi.class);
		helloApi.sayHello();  
	}
}
