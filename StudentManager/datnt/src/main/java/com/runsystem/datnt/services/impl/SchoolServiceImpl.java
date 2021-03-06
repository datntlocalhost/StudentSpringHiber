package com.runsystem.datnt.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runsystem.datnt.daos.SchoolDao;
import com.runsystem.datnt.dtos.SchoolDto;
import com.runsystem.datnt.services.SchoolService;
import com.runsystem.datnt.utils.LogginUtils;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolDao schoolDao;
	
	/*
	 * Get list of school info from database.
	 * 
	 * @return list of school
	 */
	@Transactional
	public List<SchoolDto> getSchoolList() {
		LogginUtils.getInstance().logStart(this.getClass(), "getSchoolList");
		
		List<SchoolDto> schools = new ArrayList<SchoolDto>();

		try {
			
			schools = schoolDao.list();
			
		} catch (IOException e) {
			LogginUtils.getInstance().logError(this.getClass(), e);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "getSchoolList");
		
		return schools;
	}

}
