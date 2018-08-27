package com.runsystem.datnt.entities;

import java.io.Serializable;

public class Records implements Serializable {

	private static final long serialVersionUID = 1L;
	private int     studentId;
	private String  sex;
	private String  birthday;
	private String  phone;
	private String  email;
	private String  address;
	
	public Records() {}

	public Records(int studentId, String sex, String birthday, String phone, String email, String address) {
		super();
		this.studentId = studentId;
		this.sex = sex;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
