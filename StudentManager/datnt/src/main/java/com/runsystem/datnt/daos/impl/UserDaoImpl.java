package com.runsystem.datnt.daos.impl;

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
import com.runsystem.datnt.exceptions.SelectNullException;
import com.runsystem.datnt.exceptions.UpdateException;
import com.runsystem.datnt.utils.LogginUtils;

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
	 * @throws SelectNullException
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public UserDto selectByUsername(String username) throws SelectNullException {
		LogginUtils.getInstance().logStart(this.getClass(), "selectByUsername");
		
		Session     session     = sessionFactory.getCurrentSession();
		
		String userQuery = "SELECT u.user_id as userId, u.user_username as username, u.user_password as password " + 
						   "FROM USER u " + 
						   "WHERE user_username = :username"; 
		
		UserDto user = null;

		try {
			
			Query<UserDto> query = session.createNativeQuery(userQuery);
			query.setParameter("username", username);
			query.setResultTransformer(Transformers.aliasToBean(UserDto.class));
			
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			user = query.uniqueResult();
			
			
			
		} catch (Exception ex) {
			LogginUtils.getInstance().logError(this.getClass(), ex);
		} 
		
		LogginUtils.getInstance().logResult(this.getClass(), user);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "selectByUsername");
		
		if (user == null) {
			throw new SelectNullException("Select student's account by username from database is null");
		}
		
		return user;
	}

	
	/*
	 * Insert User
	 * 
	 * @param userdto
	 * 
	 * @throws InsertException
	 */
	public void insert(UserDto user) throws InsertException {
		LogginUtils.getInstance().logStart(this.getClass(), "insert");
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "INSERT INTO USER "  	+ 
							 "	(" 					+
							 "		user_id," +
							 "		user_username," +
							 "		user_password," +
							 "		student_id" 	+
							 "	)" 					+
							 "VALUES " 				+
							 "	(" 					+ 
							 "		:userid," 	+ 
							 "		:username," 	+ 
							 "		:password," 	+ 
							 "		:studentid" 			+ 
							 "	)"; 
		
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
	 * @throws DeleteException
	 */
	public void delete(int id) throws DeleteException {
		LogginUtils.getInstance().logStart(this.getClass(), "delete");
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "DELETE u, ur " +
							 "FROM USER u, USER_ROLE ur " +
							 "WHERE " +
							 "	u.user_id = ur.user_id AND" + 
							 "	u.student_id = :id";
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
	 * @throws UpdateException
	 */
	public void update(UserDto user) throws UpdateException {
		LogginUtils.getInstance().logStart(this.getClass(), "update");
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "UPDATE USER " + 
							 "SET " +
							 "	user_username = :username," +
							 "	user_password = :password " +
							 "WHERE " +
							 "	student_id = :id ";
		
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
	 * @throws InsertException
	 */
	public void insertUserRole(int idUser, int idRole) throws InsertException {
		LogginUtils.getInstance().logStart(this.getClass(), "insertUserRole");
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "INSERT INTO USER_ROLE " +
							 "	(" +
							 "		user_id," +
							 "		role_id" +
							 "	)" + 
							 "VALUES" + 
							 "	(" +
							 "		:userid," +
							 "		:roleid" +
							 "	)";
		
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
	 *  @throws SelectNullException
	 */
	public Integer nextPK() throws SelectNullException {
		LogginUtils.getInstance().logStart(this.getClass(), "nexPK");
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "SELECT MAX(user_id) FROM USER";
		
		Integer nextId = null;
		
		boolean success = true;
		
		try {
			@SuppressWarnings("unchecked")
			Query<Integer> query = session.createNativeQuery(queryString);
			
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			nextId = query.getSingleResult();
			
		} catch (Exception ex) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logResult(this.getClass(), nextId);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "nextPK");
		
		if (nextId == null && !success) {
			throw new SelectNullException("Select max current primary key from USER table is null");
		}
		
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
	 * @throws SelectNullException
	 */
	@SuppressWarnings("deprecation")
	public UserDto selectByStudentId(int id) throws SelectNullException {
		LogginUtils.getInstance().logStart(this.getClass(), "selectByStudentId");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "SELECT " +
							 "  u.user_id as userId," +
							 "  u.user_username as username," +
							 "	u.user_password as password " +
							 "FROM" +
							 "  USER u " +
							 "WHERE" +
							 "  u.student_id = :id";
		
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
		
		if (user == null) {
			throw new SelectNullException("Select user by student's id from USER table is null");
		}
		
		return user;
	}


	@SuppressWarnings("deprecation")
	public UserDto userLogin(UserDto userInfo) throws SelectNullException {
		LogginUtils.getInstance().logStart(this.getClass(), "userLogin");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "SELECT " +
							 "  u.user_id as userId," +
							 "  u.user_username as username," +
							 "	u.user_password as password " +
							 "FROM" +
							 "  USER u " +
							 "WHERE" +
							 "  u.user_username = :username AND" +
							 "  u.user_password = :password";
		
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
		
		if (user == null) {
			throw new SelectNullException("Select user by username and password from USER table is null");
		}
		
		return user;
	}
	
}























