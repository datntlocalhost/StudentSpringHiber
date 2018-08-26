package com.runsystem.datnt.utils;

import java.sql.Timestamp;

import com.runsystem.datnt.entities.Token;

public class GenerateToken {
	
	public static Token generate(String username, int minutes) {
		
		Token token = new Token();
		
		Long times = System.currentTimeMillis();
		
		token.setUsername(username);
		token.setToken(Sha256Hash.hash(times.toString() + username));
		token.setTimestamp(new Timestamp(times + minutes*60*1000));
		
		return token;
	}
	
	public static void main(String[] args) {
		Token token = generate("user1", 30);
		System.out.println(token.toString());
	}
}