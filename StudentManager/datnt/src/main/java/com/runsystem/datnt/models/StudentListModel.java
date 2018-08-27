package com.runsystem.datnt.models;

import java.io.Serializable;

import com.runsystem.datnt.entities.Records;
import com.runsystem.datnt.entities.School;
import com.runsystem.datnt.entities.Student;

public class StudentListModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private Student student;
	private Records records;
	private School  school;
	
	public StudentListModel() {}

	public StudentListModel(Student student, Records records, School school) {
		super();
		this.student = student;
		this.records = records;
		this.school = school;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Records getRecords() {
		return records;
	}

	public void setRecords(Records records) {
		this.records = records;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
