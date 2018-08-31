package com.runsystem.datnt.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private int studentId;
	private String studentCode;
	private String studentName;
	private String schoolYear;
	private int    schoolId;
}