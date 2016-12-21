package com.imooc.action;

import com.opensymphony.xwork2.ActionSupport;

public class HelloWorldAction extends ActionSupport {


	
	public String add(){
		System.out.println("执行add方法");
		return "add";
	}
	
	public String update(){
		System.out.println("执行update方法");
		return "update";
	}
	
	
	//helloworld.action
	@Override
	public String execute() throws Exception {
		
		System.out.println("执行execute方法");
		return SUCCESS;
	}

	
}
