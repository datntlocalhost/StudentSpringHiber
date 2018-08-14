package com.runsystem.datnt.entities;

import java.io.Serializable;

public class Course implements Serializable{

	private static final long serialVersionUID = 1L;	
	private int    courseId;     
	private String courseCode; 
	private String courseName; 
	
	public Course() {}

	public Course(int courseId, String courseCode, String courseName) {
		super();
		this.courseId = courseId;
		this.courseCode = courseCode;
		this.courseName = courseName;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
