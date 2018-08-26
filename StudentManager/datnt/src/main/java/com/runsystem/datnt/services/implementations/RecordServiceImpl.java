package com.runsystem.datnt.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runsystem.datnt.daos.interfaces.RecordDao;
import com.runsystem.datnt.entities.Records;
import com.runsystem.datnt.services.interfaces.RecordService;

@Service
@Transactional
public class RecordServiceImpl implements RecordService {

	@Autowired
	private RecordDao recordDao;
	
	public Records selectById(int id) {
		return recordDao.getByKey(id);
	}

	public List<Records> selectBySex(String sex) {
		return recordDao.selectBySex(sex);
	}

}