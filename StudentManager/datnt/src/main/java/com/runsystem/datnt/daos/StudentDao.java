package com.runsystem.datnt.daos;

import java.io.IOException;
import java.util.List;

import com.runsystem.datnt.dtos.StudentDto;
import com.runsystem.datnt.dtos.StudentInfoDto;
import com.runsystem.datnt.exceptions.DeleteException;
import com.runsystem.datnt.exceptions.InsertException;
import com.runsystem.datnt.exceptions.UpdateException;
import com.runsystem.datnt.models.SearchStudentModel;

public interface StudentDao {
	
	public Integer nextPK() throws IOException;
	
	public Integer getIdByCode(String code) throws IOException;
	
	public void insert(StudentDto student) throws InsertException, IOException;
	
	public void delete(int id) throws DeleteException, IOException;
	
	public void update(StudentDto student) throws UpdateException, IOException;
	
	public String getMaxCode() throws IOException;
	
	public StudentInfoDto selectStudentByCode(String code) throws IOException;
	
	public List<StudentInfoDto> list() throws IOException;
	
	public List<StudentInfoDto> search(SearchStudentModel searchInfo) throws IOException;
}
