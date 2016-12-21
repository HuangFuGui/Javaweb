package entity;

import java.util.Date;

/**
 * Students entity. @author MyEclipse Persistence Tools
 */

public class Students implements java.io.Serializable {

	// Fields

	private String sid;
	private String sname;
	private String gender;
	private Date birthday;
	private String address;

	// Constructors

	/** default constructor */
	public Students() {
	}

	/** full constructor */
	public Students(String sname, String gender, Date birthday, String address) {
		this.sname = sname;
		this.gender = gender;
		this.birthday = birthday;
		this.address = address;
	}

	// Property accessors

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}