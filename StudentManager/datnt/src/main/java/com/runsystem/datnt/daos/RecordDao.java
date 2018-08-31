package com.runsystem.datnt.daos;

import com.runsystem.datnt.dtos.RecordDto;
import com.runsystem.datnt.exceptions.DeleteException;
import com.runsystem.datnt.exceptions.InsertException;
import com.runsystem.datnt.exceptions.UpdateException;

public interface RecordDao {

	public void insert(RecordDto record) throws InsertException;
	public void delete(int id) throws DeleteException;
	public void update(RecordDto record) throws UpdateException;
}
