package com.runsystem.datnt.daos.interfaces;

import com.runsystem.datnt.entities.Token;

public interface TokenDao {
	
	public boolean insert(Token entity);
	public boolean delete(Token entity);
	public Token selectByUsername(String username);
	public Token selectByUserToken(String username, String token);
}
