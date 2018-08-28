package com.runsystem.datnt.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runsystem.datnt.daos.interfaces.UserDao;
import com.runsystem.datnt.dtos.UserDto;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;
	
	
	public UserDto getUserByUsername(String username) {
		
		return userDao.selectByUsernam(username);
		
	}
	
}
