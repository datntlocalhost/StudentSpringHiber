package com.runsystem.datnt.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private int    userId;
	private String username;
	private String password;
	private int    studentId;
	private List<RoleDto> roles = new ArrayList<RoleDto>();
	
	public UserDto() {}

	public UserDto(int userId, String username, String password, int studentId, List<RoleDto> roles) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.studentId = studentId;
		this.roles = roles;
	}
}
