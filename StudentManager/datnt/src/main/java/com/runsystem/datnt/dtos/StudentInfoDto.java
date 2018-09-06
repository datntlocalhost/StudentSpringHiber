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

	public StudentInfoDto() {}

	public StudentInfoDto(int studentId, String studentCode, String studentName, String schoolYear, int schoolId,
			String schoolCode, String sex, Date birthday, String phone, String email, String address, String password) {
		super();
		this.studentId = studentId;
		this.studentCode = studentCode;
		this.studentName = studentName;
		this.schoolYear = schoolYear;
		this.schoolId = schoolId;
		this.schoolCode = schoolCode;
		this.sex = sex;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.password = password;
	}
}
