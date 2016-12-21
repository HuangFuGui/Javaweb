package com.Demo2.bean;

import java.util.HashSet;
import java.util.Set;

import com.Demo2.bean2.Student;

/**
 * Grade entity. @author MyEclipse Persistence Tools
 */

public class Grade implements java.io.Serializable {

	// Fields

	private Integer gid;
	private String gname;
	private String gdesc;
	//在一方定义一个多方的集合
	private Set<Student> students = new HashSet<Student>();

	// Constructors

	/** default constructor */
	public Grade() {
	}

	/** full constructor */
	public Grade(String gname, String gdesc) {
		super();
		this.gname = gname;
		this.gdesc = gdesc;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	// Property accessors
	public Integer getGid() {
		return this.gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public String getGname() {
		return this.gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getGdesc() {
		return this.gdesc;
	}

	public void setGdesc(String gdesc) {
		this.gdesc = gdesc;
	}

}