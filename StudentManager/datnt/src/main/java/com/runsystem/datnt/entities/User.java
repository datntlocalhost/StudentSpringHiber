package com.runsystem.datnt.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int       userId;  
	private String    username; 
	private String    password;
	private List<Role> roles = new ArrayList<Role>();
	
}
