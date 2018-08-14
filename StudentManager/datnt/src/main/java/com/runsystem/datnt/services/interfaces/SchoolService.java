package com.runsystem.datnt.services.interfaces;

import java.util.List;

import com.runsystem.datnt.entities.School;

public interface SchoolService {
	
	public void         insert(School school);       
	public void         update(School school);
	public void         delete(Integer schoolId);
	public School       selectById(int schoolId);
	public School       selectByCode(String code);
	public School       selectByName(String name);
	public List<School> selectAll();

}
