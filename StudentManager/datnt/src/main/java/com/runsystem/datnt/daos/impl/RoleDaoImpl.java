package com.runsystem.datnt.daos.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.runsystem.datnt.daos.RoleDao;
import com.runsystem.datnt.dtos.RoleDto;
import com.runsystem.datnt.exceptions.SelectNullException;
import com.runsystem.datnt.utils.LogginUtils;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	/*
	 * Select list of role of user from database.
	 * 
	 * @param id user
	 * 
	 * @return RoleDto list
	 * 
	 * @throws SelectNullException
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<RoleDto> getUserRole(int userId) throws SelectNullException {
		LogginUtils.getInstance().logStart(this.getClass(), "getUserRole");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "SELECT r.role_id as roleId, r.role_name as roleName " +
							 "FROM USER_ROLE ur, ROLE r " + 
							 "WHERE ur.role_id = r.role_id AND ur.user_id = :userid";
		
		List<RoleDto> roles = null;
		
		try {
			Query<RoleDto> query = session.createSQLQuery(queryString);
			query.setResultTransformer(Transformers.aliasToBean(RoleDto.class));
			
			query.setParameter("userid", userId);
			
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			roles = query.getResultList();
			
 		} catch (Exception ex) {
 			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logResult(this.getClass(), roles);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "getUserRole");
		
		if (roles == null) {
			throw new SelectNullException("Could not select list user's role from USER_ROLE table");
		}
		
		return roles;
	}

}