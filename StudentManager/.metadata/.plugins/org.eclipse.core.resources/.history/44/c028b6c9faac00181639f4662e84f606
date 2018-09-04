package com.runsystem.datnt.daos;

import java.util.List;

import com.runsystem.datnt.dtos.StudentDto;
import com.runsystem.datnt.dtos.StudentInfoDto;
import com.runsystem.datnt.exceptions.DeleteException;
import com.runsystem.datnt.exceptions.InsertException;
import com.runsystem.datnt.exceptions.SelectNullException;
import com.runsystem.datnt.exceptions.UpdateException;
import com.runsystem.datnt.models.SearchStudentModel;

public interface StudentDao {
	
	public Integer nextPK() throws SelectNullException;
	
	public Integer getIdByCode(String code) throws SelectNullException;
	
	public void insert(StudentDto student) throws InsertException;
	
	public void delete(int id) throws DeleteException;
	
	public void update(StudentDto student) throws UpdateException;
	
	public String getMaxCode();
	
	public StudentInfoDto selectStudentByCode(String code) throws SelectNullException;
	
	public List<StudentInfoDto> list() throws SelectNullException;
	
	public List<StudentInfoDto> search(SearchStudentModel searchInfo) throws SelectNullException;
}
