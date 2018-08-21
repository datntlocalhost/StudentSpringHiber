package com.runsystem.datnt.models;

import java.io.Serializable;

public class StudentModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String studentCode;
	private String studentName;
	private String sex;
	private String birthday;
	private String school;
	private String schoolYear;
	private String phone;
	private String email;
	private String address;
	private String password;
	
	public StudentModel() {}

	public StudentModel(String studentCode, String studentName, String sex, String birthday, String school,
			String schoolYear, String phone, String email, String address, String password) {
		super();
		this.studentCode = studentCode;
		this.studentName = studentName;
		this.sex = sex;
		this.birthday = birthday;
		this.school = school;
		this.schoolYear = schoolYear;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.password = password;
	}

	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "StudentModel [studentCode=" + studentCode + ", studentName=" + studentName + ", sex=" + sex
				+ ", birthday=" + birthday + ", school=" + school + ", schoolYear=" + schoolYear + ", phone=" + phone
				+ ", email=" + email + ", address=" + address + ", password=" + password + "]";
	}

	
	
}
