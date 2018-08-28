package com.runsystem.datnt.daos.implementations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.runsystem.datnt.daos.interfaces.CustomDao;

@Repository
@Transactional
public class CustomDaoImpl implements CustomDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public boolean getById(int id) {
		
		return sessionFactory.getCurrentSession().get(User.class, id) != null;
	}

}