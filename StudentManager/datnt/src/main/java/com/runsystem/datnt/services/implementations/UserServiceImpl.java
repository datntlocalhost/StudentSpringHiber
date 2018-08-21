package com.runsystem.datnt.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runsystem.datnt.daos.interfaces.UserDao;
import com.runsystem.datnt.entities.User;
import com.runsystem.datnt.services.interfaces.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	/*
	 * Retrieve user by id
	 * 
	 * @param id
	 * 
	 * @return user
	 */
	public User selectById(int id) {
		return userDao.getByKey(id);
	}

	/*
	 * Retrieve user by username
	 * 
	 * @param username
	 * 
	 * @return user
	 */
	public User selectByUsername(String username) {
		return userDao.selectByUsername(username);
	}

}
