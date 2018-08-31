package com.runsystem.datnt.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class StudentModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String studentCode;
	private String studentName;
	private String sex;
	private String birthday;
	private int    schoolId;
	private String schoolCode;
	private String schoolYear;
	private String phone;
	private String email;
	private String address;
	private String password;
}
