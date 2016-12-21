package com.imooc.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class authorityInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		if(session.get("loginusername")!=null){
			String result = invocation.invoke();
			return result;
		}
		else{
			return "login";			
		}
	}

}
