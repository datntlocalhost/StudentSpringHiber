package com.runsystem.datnt.daos.impl;

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
import com.runsystem.datnt.exceptions.SelectNullException;
import com.runsystem.datnt.utils.LogginUtils;

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
	public void insert(TokenDto token) throws InsertException {
		LogginUtils.getInstance().logStart(this.getClass(), "insert");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "INSERT INTO TOKEN " + 
							 "(	"				  + 
							 "	token_username,"  +
							 "	token_value," 	  +
							 "	token_timestamp"  +
							 ")" 				  +
							 "VALUES " 			  +
							 "(" 				  +
							 "	:username," 	  +
							 "	:value," 		  +
							 "	:timestamp" 	  +
							 ")";
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
	public TokenDto selectLastToken(String username) throws SelectNullException {
		LogginUtils.getInstance().logStart(this.getClass(), "selectLastToken");
		
		Session session = sessionFactory.getCurrentSession();
		TokenDto token = null;
		
		String queryString = "SELECT " 								  +
							 "  t.token_username as username," 		  +
							 "  t.token_value as token," 			  +
							 "	t.token_timestamp as timestamp "      +
							 "FROM " 								  + 
							 "	TOKEN t," 							  +
							 "  (" 									  +
							 "		SELECT MAX(token_id) as maxid "   +
							 "      FROM TOKEN " 					  +
							 "		WHERE token_username = :username" +
							 "	) as tk " 							  +
							 "WHERE "  								  + 
							 "	t.token_id = tk.maxid";
		
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
		
		if (token == null) {
			throw new SelectNullException("Select token from database is null.");
		}
		
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
	public void delete(String username) throws DeleteException {
		LogginUtils.getInstance().logStart(this.getClass(), "delete");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "DELETE FROM TOKEN WHERE token_username = :username";
		
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
