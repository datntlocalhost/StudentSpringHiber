package com.runsystem.datnt.entities;

import java.io.Serializable;

public class CourseResult implements Serializable {

	private static final long serialVersionUID = 1L;
	private int     resultId;
	private int     studentId;
	private int     courseId;
	private String  inYear;
	private String  term;
	private float   scores;
	
	public CourseResult() {}
	
	public CourseResult(int resultId, int studentId, int courseId, String inYear, String term, float scores) {
		super();
		this.resultId  = resultId;
		this.studentId = studentId;
		this.courseId = courseId;
		this.inYear = inYear;
		this.term = term;
		this.scores = scores;
	}
	
	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getInYear() {
		return inYear;
	}

	public void setInYear(String inYear) {
		this.inYear = inYear;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public float getScores() {
		return scores;
	}

	public void setScores(float scores) {
		this.scores = scores;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}