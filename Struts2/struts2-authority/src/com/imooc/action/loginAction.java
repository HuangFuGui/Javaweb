package com.imooc.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.imooc.po.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class loginAction extends ActionSupport implements ModelDriven<User>, SessionAware{

	User user = new User();
	
	private Map<String,Object> session = null;
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	public Map<String,Object> getSession() {
		return session;
	}
	public void setSession(Map<String,Object> session) {
		this.session = session;
	}
	
	//处理登录请求
	public String login(){
		if("admin".equals(user.getUsername())&&"123456".equals(user.getPassword())){
			
			session.put("loginusername", user.getUsername());	
			System.out.println(user.getUsername());
			return SUCCESS;
		}
		else{
			
			session.put("loginError", "用户名或密码不正确");
			return ERROR;
		}
	}

}
