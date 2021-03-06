/**
 * SearchStudentModel class
 * 
 * This model is used to send, get search info from controller to view and vice versa.
 */
package com.runsystem.datnt.models;

import java.io.Serializable;

public class SearchStudentModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String studentCode;
	private String studentName;
	private String sex;
	private String school;
	private String dateFrom;
	private String dateTo;
	
	public SearchStudentModel() {}

	public SearchStudentModel(String studentCode, String studentName, String sex, String school, String dateFrom,
			String dateTo) {
		super();
		this.studentCode = studentCode;
		this.studentName = studentName;
		this.sex = sex;
		this.school = school;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String toString() {
		return "code: " + this.studentCode + ", name: " + this.studentName + ", sex: " + this.sex + ", school: " + this.school + ", from: " + this.dateFrom + ", to: " + this.dateTo;
	}
}
