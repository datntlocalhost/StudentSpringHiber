package com.runsystem.datnt.entities;

import java.io.Serializable;
import java.util.List;

public class UserRoles implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user;
	private List<Role> roles;
	
	public UserRoles() {}

	public UserRoles(User user, List<Role> roles) {
		super();
		this.user = user;
		this.roles = roles;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
