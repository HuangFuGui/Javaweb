package schema.advice;

//为切面类
public class MoocAspect {
	public void before(){
		System.out.println("This is pointcut before");
	}
	
	public void afterReturning(){   //在方法返回之后，即执行完指定逻辑后进行。
		System.out.println("This is pointcut afterReturning");
	}
	
	public void after(){
		System.out.println("after() is done finally");
	}
}
