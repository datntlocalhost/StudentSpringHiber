package com.runsystem.datnt.daos.implementations;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.runsystem.datnt.daos.AbstractDao;
import com.runsystem.datnt.daos.interfaces.RecordsDao;
import com.runsystem.datnt.entities.Records;

@Repository
@Transactional
public class RecordsDaoImpl extends AbstractDao<Integer, Records> implements RecordsDao {

	public void insert(Records record) {
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