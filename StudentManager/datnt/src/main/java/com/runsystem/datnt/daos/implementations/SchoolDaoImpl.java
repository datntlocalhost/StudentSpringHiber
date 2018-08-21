package com.runsystem.datnt.daos.implementations;

import org.springframework.stereotype.Repository;

import com.runsystem.datnt.daos.AbstractDao;
import com.runsystem.datnt.daos.interfaces.SchoolDao;
import com.runsystem.datnt.entities.School;

@Repository
public class SchoolDaoImpl extends AbstractDao<Integer, School> implements SchoolDao {

	public School selectByCode(String code) {
		return (School) getSession().getNamedQuery("selectSchoolByCode").setParameter("schoolCode", code).uniqueResult();
	}

}
