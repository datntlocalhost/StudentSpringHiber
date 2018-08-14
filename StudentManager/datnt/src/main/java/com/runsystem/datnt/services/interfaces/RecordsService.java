package com.runsystem.datnt.services.interfaces;

import java.util.List;

import com.runsystem.datnt.entities.Records;

public interface RecordsService {
	
	public void          insert(Records record);      
	public void          update(Records record);
	public void          delete(Integer studentId);
	public Records       selectById(int studentId);
	public List<Records> selectAll();
}
