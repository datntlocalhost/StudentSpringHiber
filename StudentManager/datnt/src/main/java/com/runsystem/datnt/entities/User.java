package com.runsystem.datnt.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int       userId;  
	private String    username; 
	private String    password;
	private List<Role> roles = new ArrayList<Role>();
	
	public User() {}

	public User(int userId, String username, String password, List<Role> roles) {
		super();
		this.userId   = userId;
		this.username = username;
		this.password = password;
		this.roles    = roles;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
