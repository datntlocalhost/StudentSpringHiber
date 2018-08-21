package com.runsystem.datnt.utils;

import com.runsystem.datnt.entities.Student;
import com.runsystem.datnt.models.StudentModel;

public class ConvertObject {
	
	public static StudentModel objectToModelStudent(Student student) {
		
		if (student == null) {
			return null;
		}
		
		StudentModel studentModel = new StudentModel();
		studentModel.setStudentCode(student.getStudentCode());
		studentModel.setStudentName(student.getStudentName());
		studentModel.setSex(student.getRecord().getSex());
		studentModel.setSchool(student.getSchool().getSchoolCode());
		studentModel.setSchoolYear(student.getStartYear());
		studentModel.setBirthday(student.getRecord().getBirthday());
		studentModel.setPhone(student.getRecord().getPhone());
		studentModel.setEmail(student.getRecord().getEmail());
		studentModel.setAddress(student.getRecord().getAddress());
		
		return studentModel;
		
	}
	
	

}
