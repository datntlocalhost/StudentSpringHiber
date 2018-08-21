package com.runsystem.datnt.daos.interfaces;

import com.runsystem.datnt.daos.GenericDao;
import com.runsystem.datnt.entities.User;

public interface UserDao extends GenericDao<Integer, User> {
	
	/*
	 * Get user by username from database
	 */
	public User selectByUsername(String username);
}
