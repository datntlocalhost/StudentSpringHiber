package com.runsystem.datnt.daos.interfaces;

import com.runsystem.datnt.entities.Token;

public interface TokenDao extends GenericDaov2<Token>{
	
	public Token selectByUsername(String username);
	public Token selectByUserSeries(String username, String series);
}
