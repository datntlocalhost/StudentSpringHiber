package com.runsystem.datnt.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runsystem.datnt.daos.interfaces.RecordsDao;
import com.runsystem.datnt.entities.Records;
import com.runsystem.datnt.services.interfaces.RecordsService;

@Service
public class RecordsServiceImpl implements RecordsService {

	@Autowired
	RecordsDao recordsDao;
	
	public void insert(Records record) {
		// TODO Auto-generated method stub
		
	}

	public void update(Records record) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Integer studentId) {
		// TODO Auto-generated method stub
		
	}

	public Records selectById(int studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Records> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
