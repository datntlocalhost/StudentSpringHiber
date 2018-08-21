package com.runsystem.datnt.entities;

import java.io.Serializable;

public class PasswordReset implements Serializable {

	private static final long serialVersionUID = 1L;
	private int    id;
	private String studentCode;
	private String studentName;
	private String username;
	private String email;
	private String time;
	private String newPassword;
	private int    isProcess;
	
	public PasswordReset() {}

	public PasswordReset(int id, String studentCode, String studentName, String username, String email, String time,
			String newPassword, int isProcess) {
		super();
		this.id = id;
		this.studentCode = studentCode;
		this.studentName = studentName;
		this.username = username;
		this.email = email;
		this.time = time;
		this.newPassword = newPassword;
		this.isProcess = isProcess;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public int getIsProcess() {
		return isProcess;
	}

	public void setIsProcess(int isProcess) {
		this.isProcess = isProcess;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PasswordReset [id=" + id + ", studentCode=" + studentCode + ", studentName=" + studentName
				+ ", username=" + username + ", email=" + email + ", time=" + time + ", newPassword=" + newPassword
				+ ", isProcess=" + isProcess + "]";
	}
	

}
