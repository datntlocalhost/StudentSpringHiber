package com.runsystem.datnt.daos.impl;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.runsystem.datnt.daos.UserDao;
import com.runsystem.datnt.dtos.UserDto;
import com.runsystem.datnt.exceptions.DeleteException;
import com.runsystem.datnt.exceptions.InsertException;
import com.runsystem.datnt.exceptions.UpdateException;
import com.runsystem.datnt.utils.LogginUtils;
import com.runsystem.datnt.utils.SqlUtils;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	/*
	 * Rerieve user by username from database
	 * 
	 * @param username 
	 * 
	 * @return user
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public UserDto selectByUsername(String username) throws IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "selectByUsername");
		
		Session     session     = sessionFactory.getCurrentSession();
		
		String userQuery = SqlUtils.getSQL(SqlUtils.USER_SELECT_BY_USERNAME);
		
		UserDto user = null;

		try {
			
			Query<UserDto> query = session.createNativeQuery(userQuery);
			query.setParameter("username", username);
			query.setResultTransformer(Transformers.aliasToBean(UserDto.class));
			
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			user = query.getSingleResult();
			
		} catch (Exception ex) {
			LogginUtils.getInstance().logError(this.getClass(), ex);
		} 
		
		LogginUtils.getInstance().logResult(this.getClass(), user);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "selectByUsername");
		
		return user;
	}

	
	/*
	 * Insert User
	 * 
	 * @param userdto
	 * 
	 * @throws InsertException, IOException
	 */
	public void insert(UserDto user) throws InsertException, IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "insert");
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = SqlUtils.getSQL(SqlUtils.USER_INSERT);
		
		boolean success = true;
		
		try {
			@SuppressWarnings("unchecked")
			Query<UserDto> query = session.createNativeQuery(queryString);
			
			query.setParameter("userid", user.getUserId());
			query.setParameter("username", user.getUsername());
			query.setParameter("password", user.getPassword());
			query.setParameter("studentid", user.getStudentId());
			
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			query.executeUpdate();
			
		} catch (Exception ex) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "insert");
		
		if (!success) {
			throw new InsertException("Could not insert new user into USER table");
		}
	}

	/*
	 * Delete user by id
	 * 
	 * @param id
	 * 
	 * @throws DeleteException,IOException
	 */
	public void delete(int id) throws DeleteException, IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "delete");
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = SqlUtils.getSQL(SqlUtils.USER_DELETE);
		boolean success = true;
		
		try {
			@SuppressWarnings("rawtypes")
			Query query = session.createNativeQuery(queryString);
			
			query.setParameter("id", id);
			
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			query.executeUpdate();
			
		} catch (Exception ex) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "delete");
		
		if (!success) {
			throw new DeleteException("Could not delete user from USER table");
		}
	}

	/*
	 * Update user
	 * 
	 * @param user
	 * 
	 * @throws UpdateException, IOException
	 */
	public void update(UserDto user) throws UpdateException, IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "update");
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = SqlUtils.getSQL(SqlUtils.USER_UPDATE);
		
		boolean success = true;
		
		try {
			@SuppressWarnings("unchecked")
			Query<UserDto> query = session.createNativeQuery(queryString);
			
			query.setParameter("username", user.getUsername());
			query.setParameter("password", user.getPassword());
			query.setParameter("id", user.getStudentId());
			
			//Log info of query
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			query.executeUpdate();
			
		} catch (Exception ex) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "update");
		
		if (!success) {
			throw new UpdateException("Could not update user from USER table");
		}
	}

	/*
	 * Insert user role into USER_ROLE table.
	 * 
	 * @param id user
	 * @param id role
	 * 
	 * @throws InsertException, IOException
	 */
	public void insertUserRole(int idUser, int idRole) throws InsertException, IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "insertUserRole");
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = SqlUtils.getSQL(SqlUtils.USER_INSERT_ROLES);
		
		boolean success = true;
		
		try {
			@SuppressWarnings("rawtypes")
			Query query = session.createNativeQuery(queryString);
			
			query.setParameter("userid", idUser);
			query.setParameter("roleid", idRole);
			
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			query.executeUpdate();
			
		} catch (Exception ex) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "insertUserRole");
		
		if (!success) {
			throw new InsertException("Could not insert user role into USER_ROLE table");
		}
	}

	/*
	 * Get next user's primary key
	 * 
	 *  @return next pk
	 *  
	 *  @throws IOException
	 */
	public Integer nextPK() throws IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "nexPK");
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = SqlUtils.getSQL(SqlUtils.USER_MAX_ID);
		
		Integer nextId = null;
		
		try {
			@SuppressWarnings("unchecked")
			Query<Integer> query = session.createNativeQuery(queryString);
			
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			nextId = query.getSingleResult();
			
		} catch (Exception ex) {
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logResult(this.getClass(), nextId);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "nextPK");
		
		if (nextId == null) {
			return 1;
		}
		
		return nextId + 1;
	}

	/*
	 * Select user by student's id from database.
	 * 
	 * @param id 
	 * 
	 * @return userDto
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public UserDto selectByStudentId(int id) throws IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "selectByStudentId");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = SqlUtils.getSQL(SqlUtils.USER_SELECT_BY_STUDENTID);
		
		UserDto user = null;
		
		try {
			@SuppressWarnings("unchecked")
			Query<UserDto> query = session.createNativeQuery(queryString);
			
			query.setResultTransformer(Transformers.aliasToBean(UserDto.class));
			
			query.setParameter("id", id);
			
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			user = query.getSingleResult();
			
		} catch (Exception ex) {
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logResult(this.getClass(), user);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "selectByStudentId");
		
		return user;
	}

	/*
	 * Get user info by username and password to check login.
	 * 
	 * @param userInfo
	 * 
	 * @return userDto
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public UserDto userLogin(UserDto userInfo) throws IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "userLogin");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = SqlUtils.getSQL(SqlUtils.USER_LOGIN);
		
		UserDto user = null;
		
		try {
			@SuppressWarnings("unchecked")
			Query<UserDto> query = session.createNativeQuery(queryString);
			
			query.setResultTransformer(Transformers.aliasToBean(UserDto.class));
			
			query.setParameter("username", userInfo.getUsername());
			query.setParameter("password", userInfo.getPassword());
			
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			user = query.getSingleResult();
			
		} catch (Exception ex) {
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logResult(this.getClass(), user);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "userLogin");
		
		return user;
	}
	
}
























