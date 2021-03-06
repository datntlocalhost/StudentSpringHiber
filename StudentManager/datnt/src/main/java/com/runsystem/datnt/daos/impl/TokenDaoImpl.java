package com.runsystem.datnt.daos.impl;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.runsystem.datnt.daos.TokenDao;
import com.runsystem.datnt.dtos.TokenDto;
import com.runsystem.datnt.exceptions.DeleteException;
import com.runsystem.datnt.exceptions.InsertException;
import com.runsystem.datnt.utils.LogginUtils;
import com.runsystem.datnt.utils.SqlUtils;

@Repository
public class TokenDaoImpl implements TokenDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	/*
	 * Insert token to Token table.
	 * 
	 * @param token
	 * 
	 * @throws InsertException if insert failed.
	 */
	public void insert(TokenDto token) throws InsertException, IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "insert");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = SqlUtils.getSQL(SqlUtils.TOKEN_INSERT);
		
		boolean success = true;
		
		try {
			
			@SuppressWarnings("unchecked")
			Query<TokenDto> query = session.createNativeQuery(queryString);
			
			query.setParameter("username",  token.getUsername());
			query.setParameter("value",     token.getToken());
			query.setParameter("timestamp", token.getTimestamp());
			
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			query.executeUpdate();
			
		} catch (Exception ex) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "insert");
		
		if (!success) {
			throw new InsertException("Could not insert token into TOKEN table");
		}
	}

	/*
	 * Get last token of user.
	 * 
	 * @param username
	 * 
	 * @return tokendto
	 * 
	 * @throws SelectNullException
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public TokenDto selectLastToken(String username) throws IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "selectLastToken");
		
		Session session = sessionFactory.getCurrentSession();
		TokenDto token = null;
		
		String queryString = SqlUtils.getSQL(SqlUtils.TOKEN_LAST_TOKEN);
		
		try {
			
			Query<TokenDto> query = session.createNativeQuery(queryString);
			query.setParameter("username", username);
			query.setResultTransformer(Transformers.aliasToBean(TokenDto.class));
			
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			token = query.getSingleResult();
			
		} catch (Exception ex) {
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logResult(this.getClass(), token);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "selectLastToken");
		
		return token;
	}

	/*
	 * Delete all token has username
	 * 
	 * @param username
	 * 
	 * @throws DeleteException
	 */
	@SuppressWarnings("unchecked")
	public void delete(String username) throws DeleteException, IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "delete");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = SqlUtils.getSQL(SqlUtils.TOKEN_DELETE);
		
		boolean success = true;
		
		try {
			Query<TokenDto> query = session.createNativeQuery(queryString);
			
			query.setParameter("username", username);
			
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			query.executeUpdate();
			
		} catch (Exception ex) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "delete");
		
		if (!success) {
			throw new DeleteException("Could not delete tokens of user");
		}
	}

}
