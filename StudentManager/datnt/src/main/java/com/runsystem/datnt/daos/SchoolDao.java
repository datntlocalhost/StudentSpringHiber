package com.runsystem.datnt.daos;

import java.io.IOException;
import java.util.List;

import com.runsystem.datnt.dtos.SchoolDto;

public interface SchoolDao {

	public List<SchoolDto> list() throws IOException;
	
	public Integer getIdByCode(String code) throws IOException;
}
