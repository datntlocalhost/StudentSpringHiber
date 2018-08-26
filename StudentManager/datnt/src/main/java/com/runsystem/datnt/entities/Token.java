package com.runsystem.datnt.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class Token implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String token;
	private Timestamp timestamp;
	
	public Token() {}

	public Token(int id, String username, String token, Timestamp timestamp) {
		super();
		this.id = id;
		this.username = username;
		this.token = token;
		this.timestamp = timestamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Token [id=" + id + ", username=" + username + ", token=" + token + ", timestamp="
				+ timestamp + "]";
	}

	
}
