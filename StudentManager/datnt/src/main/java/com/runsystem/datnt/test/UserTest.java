package com.runsystem.datnt.test;

import java.io.Serializable;

public class UserTest implements Serializable {


	private static final long serialVersionUID = -3899949766713842038L;
	private String username;
	private String password;
	
	public UserTest() {}

	public UserTest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserTest [username=" + username + ", password=" + password + "]";
	}
}