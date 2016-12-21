package com.imooc.timerInterceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/*
 * 计算执行Action花费的时间
 * */
public class timerInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//在Action执行之前
		long startTime = System.currentTimeMillis();
		
		//若有多个拦截器，执行下一个拦截器；若是最后一个拦截器，调用目标Action.
		String result = invocation.invoke();
		
		//执行Action之后
		long endTime = System.currentTimeMillis();
		
		//后台输出
		System.out.println("执行Action的时间："+(endTime-startTime)+"ms");
		
		//返回视图
		return result;
	}

}
