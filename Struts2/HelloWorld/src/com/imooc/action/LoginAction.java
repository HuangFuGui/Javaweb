package com.imooc.action;

import com.imooc.po.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport  implements  ModelDriven<User>{

	/*ModelDriven模式传参*/
	private User user = new User();
	
	public String login(){
		System.out.println(user.getUsername());
		System.out.println(user.getBookList());
		System.out.println(user.getBookList().get(0));
		System.out.println(user.getBookList().get(1));
		System.out.println(user.getUserList().get(0).getPassword());
		return SUCCESS;
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
}
