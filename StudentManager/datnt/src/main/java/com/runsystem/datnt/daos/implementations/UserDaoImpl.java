package com.runsystem.datnt.daos.implementations;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.runsystem.datnt.daos.AbstractDao;
import com.runsystem.datnt.daos.interfaces.UserDao;
import com.runsystem.datnt.entities.User;

@Repository
@Transactional
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public void insert(User user) {
		
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
