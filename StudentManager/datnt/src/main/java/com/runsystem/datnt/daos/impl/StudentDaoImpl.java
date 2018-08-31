package com.runsystem.datnt.daos.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.runsystem.datnt.daos.StudentDao;
import com.runsystem.datnt.dtos.StudentDto;
import com.runsystem.datnt.dtos.StudentInfoDto;
import com.runsystem.datnt.exceptions.DeleteException;
import com.runsystem.datnt.exceptions.InsertException;
import com.runsystem.datnt.exceptions.SelectNullException;
import com.runsystem.datnt.exceptions.UpdateException;
import com.runsystem.datnt.models.SearchStudentModel;
import com.runsystem.datnt.utils.LogginUtils;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	/*
	 * Get next student's id in database
	 * 
	 * @return nex student's id 
	 * 
	 * @throws SelectNullException
	 */
	public Integer nextPK() throws SelectNullException {
		LogginUtils.getInstance().logStart(this.getClass(), "nextPK");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "SELECT MAX(student_id) FROM STUDENT";
		
		Integer id = null;
		
		boolean success = true;
		
		try {
			@SuppressWarnings("unchecked")
			Query<Integer> query = session.createSQLQuery(queryString);
		
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			id = query.getSingleResult();
			
		} catch (Exception ex) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logResult(this.getClass(), id);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "nextPK");
		
		if (id == null && !success) {
			throw new SelectNullException("Select next primary key from STUDENT talbe is null.");
		}
		
		if (id == null) {
			return 1;
		}
		
		return id + 1;
	}

	/*
	 * Insert student to database
	 * 
	 * @param student
	 * 
	 * @throw InsertException
	 */
	@SuppressWarnings("unchecked")
	public void insert(StudentDto student) throws InsertException {
		LogginUtils.getInstance().logStart(this.getClass(), "insert");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "INSERT INTO STUDENT(student_id, student_code, student_name, student_startyear, school_id) " + 
							 "VALUES(:id, :code, :name, :schoolyear, :schoolid)";
		
		boolean success = true;
		
		try {
			
			Query<StudentDto> query = session.createNativeQuery(queryString);
			
			query.setParameter("id", student.getStudentId());
			query.setParameter("code", student.getStudentCode());
			query.setParameter("name", student.getStudentName());
			query.setParameter("schoolyear", student.getSchoolYear());
			query.setParameter("schoolid", student.getSchoolId());			

			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			query.executeUpdate();		
		
		} catch (Exception ex) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "insert");
		
		if (!success) {
			throw new InsertException("Could not insert student into STUDEN table.");
		}
	}

	/*
	 * Delete student by id.
	 * 
	 * @param id
	 * 
	 * @throws DeleteException
	 */
	public void delete(int id) throws DeleteException {
		LogginUtils.getInstance().logStart(this.getClass(), "delete");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "DELETE FROM STUDENT WHERE student_id = :id";
		
		boolean success = true;
		
		try {
			@SuppressWarnings("rawtypes")
			Query query = session.createSQLQuery(queryString);
			
			query.setParameter("id", id);
			
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			query.executeUpdate();
					
		} catch (Exception ex) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "delete");
		
		if (!success) {
			throw new DeleteException("Could not delete student with id = " + id);
		}
	}

	/*
	 * Update student info.
	 * 
	 * @param student
	 * 
	 * @throws UpdateException
	 */
	public void update(StudentDto student) throws UpdateException {
		LogginUtils.getInstance().logStart(this.getClass(), "update");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "UPDATE STUDENT "                     + 
							 "SET"                                 + 
							 "   student_code = :code,"            +
							 "   student_name = :name,"            +
							 "   student_startyear = :schoolyear," +
							 "   school_id = :schoolid "           + 
							 "WHERE"                               + 
							 "   student_id = :id";
		
		boolean success = true;
		
		try {
			
			@SuppressWarnings("unchecked")
			Query<StudentDto> query = session.createNativeQuery(queryString);
			
			query.setParameter("code", student.getStudentCode());
			query.setParameter("name", student.getStudentName());
			query.setParameter("schoolyear", student.getSchoolYear());
			query.setParameter("schoolid", student.getSchoolId());
			query.setParameter("id", student.getStudentId());		

			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			query.executeUpdate();
					
		} catch (Exception ex) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "update");
		
		if (!success) {
			throw new UpdateException("Could not update student.");
		}
	}

	/*
	 * Get info of student from database by student's code.
	 * 
	 * @param code
	 * 
	 * @return studentinfo dto
	 * 
	 * @throws SelectNullException
	 */
	@SuppressWarnings("deprecation")
	public StudentInfoDto selectStudentByCode(String code) throws SelectNullException {
		LogginUtils.getInstance().logStart(this.getClass(), "selectStudentByCode");
		
		Session session = sessionFactory.getCurrentSession();
		
		StudentInfoDto student = null;
		
		String queryString = "SELECT " 								  +
							 "	 st.student_id   as studentId," 	  +
							 "	 st.student_code as studentCode," 	  +
							 "	 st.student_name as studentName," 	  +
							 "	 st.student_startyear as schoolYear," +
							 "	 sc.school_code  as schoolCode," 	  +
							 "	 r.records_sex   as sex," 			  +
							 "	 r.records_birthday as birthday," 	  +
							 "	 r.records_phone as phone," 		  +
							 "	 r.records_email as email," 		  +
							 "	 r.records_address as address " 	  +
							 "FROM STUDENT st, RECORDS r, SCHOOL sc " +
							 "WHERE " 								  + 
							 "   st.student_id = r.student_id " 	  +
							 "	 AND st.school_id = sc.school_id " 	  +
							 "	 AND st.student_code = :code";
		
		try {
			
			@SuppressWarnings("unchecked")
			Query<StudentInfoDto> query = session.createNativeQuery(queryString);
			
			query.setParameter("code", code);
			
			query.setResultTransformer(Transformers.aliasToBean(StudentInfoDto.class));

			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			student = query.getSingleResult();
			
		} catch (Exception ex) {
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logResult(this.getClass(), student);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "selectStudentByCode");
		
		if (student == null) {
			throw new SelectNullException("Select student by code from STUDENT table is null.");
		}
		
		return student;
	}

	/*
	 * Get id of student by student's code.
	 * 
	 * @param code
	 * 
	 * @return id
	 * 
	 * @throws SelectNullException
	 */
	public Integer getIdByCode(String code) throws SelectNullException {
		LogginUtils.getInstance().logStart(this.getClass(), "getIdByCode");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "SELECT st.student_id "
						   + "FROM STUDENT st "
						   + "WHERE st.student_code = :code";
		
		Integer studentId = null;
		
		try {
			@SuppressWarnings("unchecked")
			Query<Integer> query = session.createNativeQuery(queryString);
			
			query.setParameter("code", code);
			
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			studentId = query.getSingleResult();
			
		} catch (Exception ex) {
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logResult(this.getClass(), studentId);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "getIdByCode");
		
		if (studentId == null) {
			throw new SelectNullException("Select student's id by code from STUDENT table is null");
		}
		
		return studentId;
	}

	/*
	 * Get max of student code in database.
	 * 
	 * @return student code
	 * 
	 * @throws SelectNulLException
	 */
	@SuppressWarnings("unchecked")
	public String getMaxCode() {
		LogginUtils.getInstance().logStart(this.getClass(), "getMaxCode");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString  = "SELECT MAX(student_code) FROM STUDENT";
		
		String code = null;
		
		try {
			Query<String> query = session.createNativeQuery(queryString);
			
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			code = query.getSingleResult();
			
		} catch (Exception ex) {
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logResult(this.getClass(), code);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "getMaxCode");
		
		return code;
	}

	/*
	 * Select list of student
	 * 
	 * @return list of StudentInfoDto
	 *
	 * @throws SelectNullException
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<StudentInfoDto> list() throws SelectNullException {
		LogginUtils.getInstance().logStart(this.getClass(), "list");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "SELECT " 							 +
							 "  st.student_id as studentId,"      +
							 "	st.student_code as studentCode," +
							 "	st.student_name as studentName," +
							 "	st.student_startyear as schoolYear," +
							 "	st.school_id as schoolId," 		 +
							 "	sc.school_code as schoolCode," 	 +
							 "	r.records_sex as sex," 			 +
							 "	r.records_birthday as birthday," +
							 "	r.records_phone as phone," 		 +
							 "	r.records_email as email," 		 +
							 "	r.records_address as address " 	 + 
							 "FROM " 							 + 
							 "	STUDENT st," 					 +
							 "	RECORDS r," 					 +
							 "	SCHOOL sc " 					 +
							 "WHERE " 							 +
							 "	st.student_id = r.student_id AND" +
							 "	st.school_id = sc.school_id";
							 
		
		List<StudentInfoDto> students = null;
		
		try {
			Query<StudentInfoDto> query = session.createNativeQuery(queryString);
			
			query.setResultTransformer(Transformers.aliasToBean(StudentInfoDto.class));
			
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			students = query.getResultList();
			
		} catch (Exception ex) {
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logResult(this.getClass(), students);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "list");
		
		if (students == null) {
			throw new SelectNullException("Select list of student info from database is null");
		}
		
		return students;
	}

	/*
	 * Search student info.
	 * 
	 * @param searchInfo 
	 * 
	 * @return list of student info
	 * 
	 * @throws SelectNullException
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<StudentInfoDto> search(SearchStudentModel searchInfo) throws SelectNullException {
LogginUtils.getInstance().logStart(this.getClass(), "search");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "SELECT " 							 +
							 "  st.student_id as studentId,"      +
							 "	st.student_code as studentCode," +
							 "	st.student_name as studentName," +
							 "	st.student_startyear as schoolYear," +
							 "	st.school_id as schoolId," 		 +
							 "	sc.school_code as schoolCode," 	 +
							 "	r.records_sex as sex," 			 +
							 "	r.records_birthday as birthday," +
							 "	r.records_phone as phone," 		 +
							 "	r.records_email as email," 		 +
							 "	r.records_address as address " 	 + 
							 "FROM " 							 + 
							 "	STUDENT st," 					 +
							 "	RECORDS r," 					 +
							 "	SCHOOL sc " 					 +
							 "WHERE " 							 +
							 "	st.student_id = r.student_id AND" +
							 "	st.school_id = sc.school_id AND" + 
							 "  st.student_code like :studentcode AND" + 
							 "  st.student_name like :studentname AND" + 
							 "  r.records_sex like :sex AND" + 
							 "  st.school_id like :schoolid ";
		
		boolean hasDateSearch = false;
		
		if (!searchInfo.getDateFrom().isEmpty() && !searchInfo.getDateTo().isEmpty()) {
			hasDateSearch = true;
			
			queryString += "AND r.records_birthday >= :dateFrom  AND r.records_birthday <= :dateTo";
		}
		
		List<StudentInfoDto> students = null;
		
		try {
			Query<StudentInfoDto> query = session.createNativeQuery(queryString);
			
			query.setResultTransformer(Transformers.aliasToBean(StudentInfoDto.class));
			
			query.setParameter("studentcode", searchInfo.getStudentCode());
			query.setParameter("studentname", searchInfo.getStudentName());
			query.setParameter("sex", searchInfo.getSex());
			query.setParameter("schoolid", searchInfo.getSchool());
			
			if (hasDateSearch) {
				query.setParameter("dateFrom", searchInfo.getDateFrom());
				query.setParameter("dateTo", searchInfo.getDateTo());
			}

			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			students = query.getResultList();
			
		} catch (Exception ex) {
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logResult(this.getClass(), students);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "search");
		
		if (students == null) {
			throw new SelectNullException("Search list of student info from database is null");
		}
		
		return students;
	}
}