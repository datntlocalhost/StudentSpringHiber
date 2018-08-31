package com.runsystem.datnt.daos.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.runsystem.datnt.daos.SchoolDao;
import com.runsystem.datnt.dtos.SchoolDto;
import com.runsystem.datnt.exceptions.SelectNullException;
import com.runsystem.datnt.utils.LogginUtils;

@Repository
public class SchoolDaoImpl implements SchoolDao {

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * Select list school info from School table.
	 * 
	 * @return schooldto list
	 * 
	 * @throws SelectNullException
	 */
	@SuppressWarnings("deprecation")
	public List<SchoolDto> list() throws SelectNullException {
		LogginUtils.getInstance().logStart(this.getClass(), "list");
		
		Session session = sessionFactory.getCurrentSession();
		
		List<SchoolDto> schools = null;
		
		String queryString = "SELECT " 						  +
							 "sc.school_id as schoolId, "     +
							 "sc.school_code as schoolCode, " +
							 "sc.school_name as schoolName "   +
							 "FROM SCHOOL sc";
		
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
		
		if (schools == null) {
			throw new SelectNullException("Select list school from SCHOOL table is null");
		}
		
		return schools;
	}

	/*
	 * Select school's id by code.
	 * 
	 * @param code
	 * 
	 * @return school's id
	 * 
	 * @throws SelectNullException
	 */
	public Integer getIdByCode(String code) throws SelectNullException {
		LogginUtils.getInstance().logStart(this.getClass(), "getIdByCode");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "SELECT sc.school_id "
						   + "FROM SCHOOL sc "
						   + "WHERE sc.school_code = :code";
		
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
		
		if (schoolId == null) {
			throw new SelectNullException("Select school's id by code from SCHOOL table is null");
		}
		
		return schoolId;
	}
}
