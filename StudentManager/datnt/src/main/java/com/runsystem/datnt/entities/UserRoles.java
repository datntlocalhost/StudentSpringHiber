package com.runsystem.datnt.entities;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoles implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user;
	private List<Role> roles;
}
