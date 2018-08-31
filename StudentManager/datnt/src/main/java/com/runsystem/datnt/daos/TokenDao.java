package com.runsystem.datnt.daos;

import com.runsystem.datnt.dtos.TokenDto;
import com.runsystem.datnt.exceptions.DeleteException;
import com.runsystem.datnt.exceptions.InsertException;
import com.runsystem.datnt.exceptions.SelectNullException;

public interface TokenDao {

	public void insert(TokenDto token) throws InsertException;
	public void delete(String username) throws DeleteException;
	public TokenDto selectLastToken(String username) throws SelectNullException;
}
