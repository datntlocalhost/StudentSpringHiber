package com.runsystem.datnt.entities;

import java.io.Serializable;

public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	private int     studentId;     
	private String  studentCode;
	private String  studentName;
	private String  startYear;  //start year study in school.
	private School  school;
	private Records record;
	
	public Student() {}

	public Student(int studentId, String studentCode, String studentName, String startYear, School school,
			Records record) {
		super();
		this.studentId = studentId;
		this.studentCode = studentCode;
		this.studentName = studentName;
		this.startYear = startYear;
		this.school = school;
		this.record = record;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Records getRecord() {
		return record;
	}

	public void setRecord(Records record) {
		this.record = record;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}