package com.runsystem.datnt.daos.implementations;

import org.springframework.stereotype.Repository;

import com.runsystem.datnt.daos.AbstractDao;
import com.runsystem.datnt.daos.interfaces.UserDao;
import com.runsystem.datnt.entities.User;

@Repository
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
	
	/*
	 * Rerieve user by username from database
	 * 
	 * @param username 
	 * 
	 * @return user
	 */
	public User selectByUsername(String username) {
		return buildQuery("selectByUsername").setParameter("username", username).uniqueResult();
	}

}
