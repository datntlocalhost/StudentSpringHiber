package com.runsystem.datnt.daos.implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.runsystem.datnt.daos.interfaces.SchoolDao;
import com.runsystem.datnt.entities.School;

@Repository
public class SchoolDaoImpl implements SchoolDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<School> list() {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<School> schools = new ArrayList<School>();
		
		String queryString = "SELECT * FROM SCHOOL";
		
		try {
			transaction = session.beginTransaction();
			
			Query<School> query = session.createSQLQuery(queryString).addEntity(School.class);
			schools = query.getResultList();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
		
		return schools;
	}

	public String getCode(String id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		String school = null;
		
		String queryString = "SELECT school_code FROM SCHOOL WHERE school_id = :id";
		
		try {
			transaction = session.beginTransaction();
			school = (String) session.createSQLQuery(queryString)
											.setParameter("id", id)
											.uniqueResult();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}	
		return school;
	}
	
	
}
