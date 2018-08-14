package com.runsystem.datnt.entities;

import java.io.Serializable;

public class School implements Serializable {

	private static final long serialVersionUID = 1L;
	private int schoolId;      //School's id
	private String schoolCode; //School's chode
	private String schoolName; //School's name
	
	public School() {}
	
	public School(int schoolId, String schoolCode, String schoolName) {
		super();
		this.schoolId = schoolId;
		this.schoolCode = schoolCode;
		this.schoolName = schoolName;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
