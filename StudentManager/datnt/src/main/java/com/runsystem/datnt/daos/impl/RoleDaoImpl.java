package com.runsystem.datnt.daos.impl;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.runsystem.datnt.daos.RoleDao;
import com.runsystem.datnt.dtos.RoleDto;
import com.runsystem.datnt.utils.LogginUtils;
import com.runsystem.datnt.utils.SqlUtils;

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
	 * @throws IOException
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<RoleDto> getUserRole(int userId) throws IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "getUserRole");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = SqlUtils.getSQL(SqlUtils.ROLE_SELECT_BY_USERID);
		
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
		
		return roles;
	}

}
