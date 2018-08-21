package com.runsystem.datnt.daos.interfaces;

import com.runsystem.datnt.daos.GenericDao;
import com.runsystem.datnt.entities.School;

public interface SchoolDao extends GenericDao<Integer, School>{
	public School selectByCode(String code);
}
