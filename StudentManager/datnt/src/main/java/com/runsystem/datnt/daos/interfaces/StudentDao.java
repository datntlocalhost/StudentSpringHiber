package com.runsystem.datnt.daos.interfaces;

import java.util.List;

import com.runsystem.datnt.daos.GenericDao;
import com.runsystem.datnt.entities.Student;
import com.runsystem.datnt.models.SearchStudentModel;

public interface StudentDao extends GenericDao<Integer, Student> {
	
	public String getLastStudentCode();
	public Student selectByCode(String code);
	public List<Student> search(SearchStudentModel searchInfo);
}
