package com.runsystem.datnt.daos.interfaces;

import java.util.List;

import com.runsystem.datnt.daos.GenericDao;
import com.runsystem.datnt.entities.Records;

public interface RecordDao extends GenericDao<Integer, Records>{

	public List<Records> selectBySex(String sex);
}
