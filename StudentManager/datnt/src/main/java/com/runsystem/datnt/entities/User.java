package com.runsystem.datnt.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int       userId;  
	private String    username; 
	private String    password;
	private Student   student;
	private Set<Role> roles = new HashSet<Role>(0);
	
	public User() {}

	public User(int userId, String username, String password, Student student, Set<Role> roles) {
		super();
		this.userId   = userId;
		this.username = username;
		this.password = password;
		this.student  = student;
		this.roles    = roles;
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
