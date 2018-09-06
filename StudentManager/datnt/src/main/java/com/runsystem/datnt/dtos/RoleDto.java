package com.runsystem.datnt.dtos;

import java.io.Serializable;

import lombok.Data;

@Data
public class RoleDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int roleId;
	private String roleName;
	
	public RoleDto() {}

	public RoleDto(int roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}
}
