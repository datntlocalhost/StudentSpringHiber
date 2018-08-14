package com.runsystem.datnt.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runsystem.datnt.daos.interfaces.UserDao;
import com.runsystem.datnt.entities.User;
import com.runsystem.datnt.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	public boolean insert(User user) {
		User user2 = selectByName(user.getUsername());
		
		if (user2 == null) {
			userDao.insert(user);
			return true;
		}
		return false;
	}

	public boolean update(User user) {
		return false;
	}

	public boolean delete(Integer userId) {
		return false;
	}

	public User selectById(int userId) {
		return null;
	}

	public User selectByName(String name) {
		return null;
	}

	public List<User> selectAll() {
		return null;
	}
}
