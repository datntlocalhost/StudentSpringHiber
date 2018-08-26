package com.runsystem.datnt.daos.implementations;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
	@SuppressWarnings("unchecked")
	public User selectByUsername(String username) {
		
		Session     session     = getSession();
		Transaction transaction = null;
		
		String stringQuery = "SELECT * FROM USER WHERE user_username = :username"; 
		
		User user = null;

		try {
			
			transaction = session.beginTransaction();
			Query<User> query = session.createSQLQuery(stringQuery).addEntity(User.class);
			query.setParameter("username", username);
			user = query.uniqueResult();
			
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
		
		return user;
	}

}
