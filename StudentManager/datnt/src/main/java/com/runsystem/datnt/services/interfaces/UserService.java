package com.runsystem.datnt.services.interfaces;

import com.runsystem.datnt.entities.User;

public interface UserService {

	public User selectById(int id);
	public User selectByUsername(String username);
}
