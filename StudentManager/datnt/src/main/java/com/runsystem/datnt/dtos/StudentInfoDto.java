package com.runsystem.datnt.dtos;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

@Data
public class StudentInfoDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int    studentId;
	private String studentCode;
	private String studentName;
	private String schoolYear;
	private int    schoolId;
	private String schoolCode;
	private String sex;
	private Date   birthday;
	private String phone;
	private String email;
	private String address;
	private String password;

}