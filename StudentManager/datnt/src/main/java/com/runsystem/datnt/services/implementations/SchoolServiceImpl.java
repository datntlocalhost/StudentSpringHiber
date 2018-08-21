package com.runsystem.datnt.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runsystem.datnt.daos.interfaces.SchoolDao;
import com.runsystem.datnt.entities.School;
import com.runsystem.datnt.services.interfaces.SchoolService;

@Service
@Transactional
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolDao schoolDao;
	
	public List<School> selectAll() {
		return schoolDao.list();
	}

}
