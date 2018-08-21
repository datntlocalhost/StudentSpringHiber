package com.runsystem.datnt.daos.implementations;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.runsystem.datnt.daos.AbstractDao;
import com.runsystem.datnt.daos.interfaces.StudentDao;
import com.runsystem.datnt.entities.Student;
import com.runsystem.datnt.models.SearchStudentModel;

@Repository
public class StudentDaoImpl extends AbstractDao<Integer, Student> implements StudentDao {

	
	/*
	 * Retrieve last student's code in database
	 * 
	 * @return string 
	 */
	public String getLastStudentCode() {
		return (String) getSession().getNamedQuery("getLastStudentCode").uniqueResult();
	}

	/*
	 * Retrieve student by student's code
	 * 
	 * @param code 
	 * 
	 * @return student object
	 */
	public Student selectByCode(String code) {
		return (Student) getSession().getNamedQuery("selectStudentByCode").setParameter("studentcode", code).uniqueResult();
	}

	/*
	 * Search student and retrieve list of student from database.
	 * 
	 * @param searchInfo
	 * 
	 * @return list of student.
	 */
	public List<Student> search(SearchStudentModel searchInfo) {
		Query<Student> query;
		
		String dateFrom = "";
		String dateTo   = "";
		
		if (searchInfo.getDateFrom() == null || searchInfo.getDateFrom().isEmpty()) {
			query = buildQuery("searchStudent");
		} else {
			query = buildQuery("searchStudentDateRange");
			query.setParameter("from", dateFrom);
			query.setParameter("to", dateTo);
		}
		
		query.setParameter("code", searchInfo.getStudentCode());
		query.setParameter("name", searchInfo.getStudentName());
		query.setParameter("sex", searchInfo.getSex());
		query.setParameter("school", searchInfo.getSchool());
		
		return query.getResultList();
	}
}
