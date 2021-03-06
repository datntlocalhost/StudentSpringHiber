package com.runsystem.datnt.services;

import java.util.List;

import com.runsystem.datnt.dtos.StudentInfoDto;
import com.runsystem.datnt.exceptions.DeleteException;
import com.runsystem.datnt.exceptions.InsertException;
import com.runsystem.datnt.exceptions.UpdateException;
import com.runsystem.datnt.models.SearchStudentModel;
import com.runsystem.datnt.models.StudentModel;


public interface StudentService {
	
	public void deleteStudent(String studentCode) throws DeleteException;
	
	public void updateStudent(StudentModel studentInfo) throws UpdateException;
	
	public StudentModel createStudent(StudentModel studentInfo) throws InsertException;
	
	public StudentInfoDto selectByCode(String code);
	
	public String getMaxCurrentCode();
	
	public List<StudentInfoDto> getStudentList();
	
	public List<StudentInfoDto> searchStudent(SearchStudentModel searchInfo);
}
