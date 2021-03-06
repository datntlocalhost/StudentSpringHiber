package com.runsystem.datnt.daos;

import java.io.IOException;

import com.runsystem.datnt.dtos.RecordDto;
import com.runsystem.datnt.exceptions.DeleteException;
import com.runsystem.datnt.exceptions.InsertException;
import com.runsystem.datnt.exceptions.UpdateException;

public interface RecordDao {

	public void insert(RecordDto record) throws InsertException, IOException;
	public void delete(int id) throws DeleteException, IOException;
	public void update(RecordDto record) throws UpdateException, IOException;
}
