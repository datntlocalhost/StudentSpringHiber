package com.runsystem.datnt.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class TokenDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String token;
	private Timestamp timestamp;
	
	public TokenDto() {}

	public TokenDto(String username, String token, Timestamp timestamp) {
		super();
		this.username = username;
		this.token = token;
		this.timestamp = timestamp;
	}
}
