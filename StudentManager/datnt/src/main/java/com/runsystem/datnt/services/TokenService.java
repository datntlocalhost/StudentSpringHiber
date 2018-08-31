package com.runsystem.datnt.services;

import com.runsystem.datnt.dtos.TokenDto;

public interface TokenService {

	public TokenDto createToken(String username);
	
	public TokenDto getLastToken(String username);
}