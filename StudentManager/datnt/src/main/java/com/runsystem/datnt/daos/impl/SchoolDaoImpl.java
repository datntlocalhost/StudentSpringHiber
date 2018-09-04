package com.runsystem.datnt.daos.impl;

import java.io.IOException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.runsystem.datnt.daos.SchoolDao;
import com.runsystem.datnt.dtos.SchoolDto;
import com.runsystem.datnt.utils.LogginUtils;
import com.runsystem.datnt.utils.SqlUtils;

@Repository
public class SchoolDaoImpl implements SchoolDao {

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * Select list school info from School table.
	 * 
	 * @return schooldto list
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public List<SchoolDto> list() throws IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "list");
		
		Session session = sessionFactory.getCurrentSession();
		
		List<SchoolDto> schools = null;
		
		String queryString = SqlUtils.getSQL(SqlUtils.SCHOOL_LIST);
		
		try {
			@SuppressWarnings("unchecked")
			Query<SchoolDto> query = session.createNativeQuery(queryString);
			query.setResultTransformer(Transformers.aliasToBean(SchoolDto.class));
			
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			schools = query.getResultList();

		} catch (Exception ex) {
			LogginUtils.getInstance().logError(this.getClass(), ex);
		} 
		
		LogginUtils.getInstance().logResult(this.getClass(), schools);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "list");
		
		return schools;
	}

	/*
	 * Select school's id by code.
	 * 
	 * @param code
	 * 
	 * @return school's id
	 * 
	 * @throws IOException
	 */
	public Integer getIdByCode(String code) throws IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "getIdByCode");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = SqlUtils.getSQL(SqlUtils.SCHOOL_ID_BY_CODE);
		
		Integer schoolId = null;
		
		try {
			@SuppressWarnings("unchecked")
			Query<Integer> query = session.createNativeQuery(queryString);
			
			query.setParameter("code", code);
			
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			schoolId = query.getSingleResult();
			
		} catch (Exception ex) {
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logResult(this.getClass(), schoolId);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "getIdByCode");
		
		return schoolId;
	}
}
