package com.runsystem.datnt.entities;

import java.io.Serializable;

public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	private int    roleId;   //Role's id
	private String roleName; //Role's name
	
	public Role() {}
	
	public Role(int roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}