package com.runsystem.datnt.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	private int     studentId;     
	private String  studentCode;
	private String  studentName;
	private String  startYear;  //start year study in school.
	private int     schoolId;
}
