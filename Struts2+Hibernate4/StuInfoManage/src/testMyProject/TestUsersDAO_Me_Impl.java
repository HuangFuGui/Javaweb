package testMyProject;

import junit.framework.Assert;

import org.junit.Test;

import service.UsersDAO_Me;
import serviceImpl.UsersDAO_Me_Impl;
import entity.Users;

public class TestUsersDAO_Me_Impl {
	@Test
	public void testUsersLogin(){
		Users u = new Users("zhangsan","12356");
		UsersDAO_Me udao = new UsersDAO_Me_Impl();  //接口类型
	//	Assert.assertEquals(true, udao.usersLogin(u));
		if(udao.usersLogin(u)){
			System.out.print("1");
		}
		else{
			System.out.print("0");
		}

	}

}
