package com.runsystem.datnt.services.interfaces;

import java.util.List;

import com.runsystem.datnt.entities.Records;

public interface RecordService {
	
	public Records selectById(int id);
	public List<Records> selectBySex(String sex);

}
