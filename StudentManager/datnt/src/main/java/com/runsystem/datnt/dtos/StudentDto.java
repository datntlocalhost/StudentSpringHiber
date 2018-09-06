package com.runsystem.datnt.dtos;

import java.io.Serializable;

import lombok.Data;

@Data
public class StudentDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private int studentId;
	private String studentCode;
	private String studentName;
	private String schoolYear;
	private int    schoolId;
	
	public StudentDto() {}

	public StudentDto(int studentId, String studentCode, String studentName, String schoolYear, int schoolId) {
		super();
		this.studentId = studentId;
		this.studentCode = studentCode;
		this.studentName = studentName;
		this.schoolYear = schoolYear;
		this.schoolId = schoolId;
	}
}
