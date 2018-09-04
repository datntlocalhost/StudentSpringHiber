package com.runsystem.datnt.services;

import javax.servlet.http.HttpSession;

import com.runsystem.datnt.dtos.TokenDto;
import com.runsystem.datnt.exceptions.AuthException;

public interface TokenService {

	public TokenDto createToken(String username);
	
	public TokenDto getLastToken(String username);
	
	public void checkValidToken(HttpSession session) throws AuthException;
}
