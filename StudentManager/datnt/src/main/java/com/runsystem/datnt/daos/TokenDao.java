package com.runsystem.datnt.daos;

import java.io.IOException;

import com.runsystem.datnt.dtos.TokenDto;
import com.runsystem.datnt.exceptions.DeleteException;
import com.runsystem.datnt.exceptions.InsertException;

public interface TokenDao {

	public void insert(TokenDto token) throws InsertException, IOException;
	public void delete(String username) throws DeleteException, IOException;
	public TokenDto selectLastToken(String username) throws IOException;
}
