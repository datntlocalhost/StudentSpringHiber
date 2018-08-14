package com.runsystem.datnt.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runsystem.datnt.daos.interfaces.SchoolDao;
import com.runsystem.datnt.entities.School;
import com.runsystem.datnt.services.interfaces.SchoolService;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolDao schoolDao;

	public void insert(School school) {
		schoolDao.insert(school);
	}

	public void update(School school) {
		schoolDao.update(school);
	}

	public void delete(Integer schoolId) {
		schoolDao.delete(schoolId);
	}

	public School selectById(int schoolId) {
		return schoolDao.selectById(schoolId);
	}

	public School selectByCode(String code) {
		return schoolDao.selectByCode(code);
	}

	public School selectByName(String name) {
		return schoolDao.selectByName(name);
	}

	public List<School> selectAll() {
		return null;
	}
}
