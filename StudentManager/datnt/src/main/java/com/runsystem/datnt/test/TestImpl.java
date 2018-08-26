package com.runsystem.datnt.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.runsystem.datnt.entities.User;

@Repository
public class TestImpl implements Test {

	@Autowired
	private SessionFactory sessionFactory;
	
	public UserTest getUserById(int id) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		String query = "SELECT * FROM USER WHERE user_username = ?";
		UserTest userTest = null;
		
		try {
			transaction = session.beginTransaction();
			Object user = session.createNativeQuery(query, User.class).setParameter(1, "admin").uniqueResult();
			
			User userObj = (User) user;
			
			userTest = new UserTest(userObj.getUsername(), userObj.getPassword());
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return userTest;
	}

}
