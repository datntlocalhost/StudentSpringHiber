package com.runsystem.datnt.daos.implementations;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.runsystem.datnt.daos.AbstractDao;
import com.runsystem.datnt.daos.interfaces.SchoolDao;
import com.runsystem.datnt.entities.School;

@Repository
@Transactional(rollbackFor = Exception.class)
public class SchoolDaoImpl extends AbstractDao<Integer, School> implements SchoolDao {

	public void insert(School school) {
		persist(school);
	}

	public void delete(Integer schoolId) {
		delete(schoolId);
	}

	public School selectById(int schoolId) {
		return getByKey(schoolId);
	}

	public School selectByCode(String code) {
		return null;
	}

	public List<School> selectAll() {
		return null;
	}

	public School selectByName(String name) {
		return null;
	}
}