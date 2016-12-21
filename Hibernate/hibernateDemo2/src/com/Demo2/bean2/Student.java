package com.Demo2.bean2;

import com.Demo2.bean.Grade;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	// Fields

	private Integer sid;
	private String sname;
	private String sex;
	
	//在多方定义一个一方的引用
	private Grade grade;

	// Constructors


	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	/** default constructor */
	public Student() {
	}

	/** full constructor */
	// Property accessors

	public Student(String sname, String sex) {
		super();
		this.sname = sname;
		this.sex = sex;
	}

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}