package com.runsystem.datnt.utils;

import java.sql.Timestamp;

import com.runsystem.datnt.dtos.TokenDto;

public class GenerateToken {
	
	public static TokenDto generate(String username, int minutes) {
		
		TokenDto token = new TokenDto();
		
		Long times = System.currentTimeMillis();
		
		token.setUsername(username);
		token.setToken(Sha256Hash.hash(times.toString() + username + "datnt!@#"));
		token.setTimestamp(new Timestamp(times + minutes*60*1000));
		
		return token;
	}
}
