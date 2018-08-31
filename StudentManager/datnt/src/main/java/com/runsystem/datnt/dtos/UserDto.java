package com.runsystem.datnt.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class UserDto {
	private int    userId;
	private String username;
	private String password;
	private int    studentId;
	private List<RoleDto> roles = new ArrayList<RoleDto>();
}
