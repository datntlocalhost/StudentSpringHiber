package com.runsystem.datnt.daos.implementations;

import java.util.List;
import java.util.Set;

import javax.persistence.Parameter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Transformer;
import org.springframework.stereotype.Repository;

import com.runsystem.datnt.daos.interfaces.UserDao;
import com.runsystem.datnt.dtos.UserDto;
import com.runsystem.datnt.entities.Role;
import com.runsystem.datnt.entities.User;

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
	 */
	@SuppressWarnings("unchecked")
	public User selectByUsername(String username) {
		
		Session     session     = sessionFactory.openSession();
		Transaction transaction = null;
		
		String userQuery = "SELECT * FROM USER WHERE user_username = :username"; 
		String roleQuery = "SELECT r.* FROM USER_ROLE ur, ROLE r WHERE ur.role_id=r.role_id AND user_id = :userid";
		
		User user = null;

		try {
			
			transaction = session.beginTransaction();
			Query<User> queryUser = session.createSQLQuery(userQuery).addEntity(User.class);
			queryUser.setParameter("username", username);
			user = queryUser.uniqueResult();
			
			if (user != null) {
				Query<Role> queryRole = session.createSQLQuery(roleQuery).addEntity(Role.class);
				queryRole.setParameter("userid", user.getUserId());
				List<Role> roles = queryRole.getResultList();
				user.setRoles(roles);
			}
			
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

	
	@SuppressWarnings("deprecation")
	public UserDto selectByUsernam(String username) {
		
		String queryString = "SELECT u.user_username as username, u.user_password as password FROM USER u WHERE u.user_username = :username";
		
		Query<UserDto> query = sessionFactory.getCurrentSession().createNativeQuery(queryString, UserDto.class);
		query.setParameter("username", username);
		query.setResultTransformer(Transformers.aliasToBean(UserDto.class));
		
		UserDto user = query.getSingleResult();
		
		return user;
	}
}
