package com.runsystem.datnt.daos.impl;

import java.io.IOException;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.runsystem.datnt.daos.RecordDao;
import com.runsystem.datnt.dtos.RecordDto;
import com.runsystem.datnt.exceptions.DeleteException;
import com.runsystem.datnt.exceptions.InsertException;
import com.runsystem.datnt.exceptions.UpdateException;
import com.runsystem.datnt.utils.LogginUtils;
import com.runsystem.datnt.utils.SqlUtils;

@Repository
public class RecordDaoImpl implements RecordDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * Insert record to Record Table.
	 * 
	 * @param recorddto
	 * 
	 * @throws InsertException, IOException
	 */
	public void insert(RecordDto record) throws InsertException, IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "insert");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = SqlUtils.getSQL(SqlUtils.RECORDS_INSERT);
		
		boolean success = true;
		
		try {
			@SuppressWarnings("unchecked")
			Query<RecordDto> query = session.createNativeQuery(queryString);
			
			query.setParameter("id",      record.getRecordId());
			query.setParameter("sex",     record.getSex());
			query.setParameter("birthday",record.getBirthday());
			query.setParameter("phone",   record.getPhone());
			query.setParameter("email",   record.getEmail());
			query.setParameter("address", record.getAddress());
			
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			query.executeUpdate();
		} catch (Exception ex) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "insert");
		
		if (!success) {
			throw new InsertException("Could not insert student's record into RECORDS table");
		}
	}

	/*
	 * Delete student record by id.
	 * 
	 * @param id
	 * 
	 * @throws DeleteException, IOException
	 */
	public void delete(int id) throws DeleteException, IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "delete");
		
		Session session = sessionFactory.getCurrentSession();
				
		String queryString = SqlUtils.getSQL(SqlUtils.RECORDS_DELETE);
		
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
			throw new DeleteException("Could not delete record from RECORDS table.");
		}
	}

	/*
	 * Update student records.
	 * 
	 * @param record
	 * 
	 * @throws UpdateException, IOException
	 */
	public void update(RecordDto record) throws UpdateException, IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "update");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = SqlUtils.getSQL(SqlUtils.RECORDS_UPDATE);
		
		boolean success = true;
		
		try {
			@SuppressWarnings("unchecked")
			Query<RecordDto> query = session.createNativeQuery(queryString);
			
			query.setParameter("sex",      record.getSex());
			query.setParameter("birthday", record.getBirthday());
			query.setParameter("phone",    record.getPhone());
			query.setParameter("email",    record.getEmail());
			query.setParameter("address",  record.getAddress());
			query.setParameter("id", 	   record.getRecordId());
			
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			query.executeUpdate();
			
		} catch (Exception ex) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "update");
		
		if (!success) {
			throw new UpdateException("Could not update record from RECORDS table");
		}
	}

}
