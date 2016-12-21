package com.imooc.po;

import java.util.List;

public class User {

	private String username;
	private String password;
	private List<String> bookList;
	private List<User> userList;
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	public List<String> getBookList() {
		return bookList;
	}
	public void setBookList(List<String> bookList) {
		this.bookList = bookList;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
