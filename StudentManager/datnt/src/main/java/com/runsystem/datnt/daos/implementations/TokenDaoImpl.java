package com.runsystem.datnt.daos.implementations;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.runsystem.datnt.daos.interfaces.TokenDao;
import com.runsystem.datnt.entities.Token;

@Repository
public class TokenDaoImpl implements TokenDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public boolean insert(Token entity) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		boolean success = false;
		
		String queryString = "INSERT INTO TOKEN(token_username, token_value, token_timestamp) " +
						     "VALUES(?, ?, ?)";
		
		try {
			transaction = session.beginTransaction();
			Query<Token> query = session.createSQLQuery(queryString);
			query.setParameter(1, entity.getUsername());
			query.setParameter(2, entity.getToken());
			query.setParameter(3, entity.getTimestamp());
			success = query.executeUpdate() > 0 ? true : false;
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
		
		return success;
	}

	public boolean update(Token entity) {
		return false;
	}

	public boolean delete(Token entity) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		boolean success = false;
		
		String queryString = "DELETE FROM TOKEN WHERE token_username = :username AND token_value = :token";
		
		try {
			transaction = session.beginTransaction();
			Query<Token> query = session.createSQLQuery(queryString);
			query.setParameter("username", entity.getUsername());
			query.setParameter("token", entity.getToken());
			success = query.executeUpdate() > 0 ? true : false;
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
		
		return success;
	}

	public Token select(int id) {
		return null;
	}

	public List<Token> list() {
		return null;
	}

	public Token selectByUsername(String username) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Token token = null;
		
		String queryString = "SELECT * FROM TOKEN WHERE token_username = :username";
		
		try {
			transaction = session.beginTransaction();
			Query<Token> query = session.createSQLQuery(queryString).addEntity(Token.class);
			query.setParameter("username", username);
			token = query.uniqueResult();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
		
		return token;
	}

	public Token selectByUserSeries(String username, String tokenValue) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Token token = null;
		String queryString = "SELECT * FROM TOKEN WHERE token_username = :username AND token_value = :token";
		
		try {
			transaction = session.beginTransaction();
			Query<Token> query = session.createSQLQuery(queryString).addEntity(Token.class);
			query.setParameter("username", username);
			query.setParameter("token", tokenValue);
			token = query.uniqueResult();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
		
		return token;
	}

}