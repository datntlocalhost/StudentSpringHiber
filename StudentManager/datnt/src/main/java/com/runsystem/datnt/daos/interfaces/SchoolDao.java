package com.runsystem.datnt.daos.interfaces;

import java.util.List;

import com.runsystem.datnt.entities.School;

public interface SchoolDao {
	
	public String getCode(String id);
	public List<School> list();
}
