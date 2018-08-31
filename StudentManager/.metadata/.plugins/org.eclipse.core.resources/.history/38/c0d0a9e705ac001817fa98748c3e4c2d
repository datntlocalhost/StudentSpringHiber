package com.runsystem.datnt.daos.interfaces;

import java.util.List;

import com.runsystem.datnt.models.SearchStudentModel;
import com.runsystem.datnt.models.StudentListModel;
import com.runsystem.datnt.models.StudentModel;

public interface StudentDao {
	
	public boolean insert(StudentModel studentInfo);
	public boolean update(StudentModel studentInfo);
	public boolean delete(String studentCode);
	public StudentModel searchByCode(String code);
	public List<StudentListModel> search(SearchStudentModel searchInfo);
	public List<StudentListModel> list();
	public String getMaxCode();

}
