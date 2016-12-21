package com.dao;

import com.po.Users;

/*
 *业务逻辑类 
 * */
public class UsersDAO {
	/*用户登录逻辑*/
	public boolean usersLogin(Users u)
	{
		if("admin".equals(u.getUsername())&&"admin".equals(u.getPassword()))
		{
			return true;
		}
		else
		{
		   return false;
		}		
	}

}
