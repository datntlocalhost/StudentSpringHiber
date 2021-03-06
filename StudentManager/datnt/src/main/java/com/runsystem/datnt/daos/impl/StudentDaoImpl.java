package com.runsystem.datnt.daos.impl;

import java.io.IOException;
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
import com.runsystem.datnt.exceptions.UpdateException;
import com.runsystem.datnt.models.SearchStudentModel;
import com.runsystem.datnt.utils.LogginUtils;
import com.runsystem.datnt.utils.SqlUtils;

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
	public Integer nextPK() throws IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "nextPK");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = SqlUtils.getSQL(SqlUtils.STUDENT_MAX_ID);
		
		Integer id = null;
		
		try {
			@SuppressWarnings("unchecked")
			Query<Integer> query = session.createNativeQuery(queryString);
		
			LogginUtils.getInstance().logQuery(this.getClass(), query);
			
			id = query.getSingleResult();
			
		} catch (Exception ex) {
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logResult(this.getClass(), id);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "nextPK");
		
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
	public void insert(StudentDto student) throws InsertException, IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "insert");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = SqlUtils.getSQL(SqlUtils.STUDENT_INSERT);
		
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
	public void delete(int id) throws DeleteException, IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "delete");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = SqlUtils.getSQL(SqlUtils.STUDENT_DELETE);
		
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
	public void update(StudentDto student) throws UpdateException, IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "update");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = SqlUtils.getSQL(SqlUtils.STUDENT_UPDATE);
		
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
	public StudentInfoDto selectStudentByCode(String code) throws IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "selectStudentByCode");
		
		Session session = sessionFactory.getCurrentSession();
		
		StudentInfoDto student = null;
		
		String queryString = SqlUtils.getSQL(SqlUtils.STUDENT_SELECT_BY_CODE);
		
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
	public Integer getIdByCode(String code) throws IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "getIdByCode");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = SqlUtils.getSQL(SqlUtils.STUDENT_ID_BY_CODE);
		
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
	public String getMaxCode() throws IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "getMaxCode");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString  = SqlUtils.getSQL(SqlUtils.STUDENT_MAX_CODE);
		
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
	public List<StudentInfoDto> list() throws IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "list");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = SqlUtils.getSQL(SqlUtils.STUDENT_LIST);
							 
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
	public List<StudentInfoDto> search(SearchStudentModel searchInfo) throws IOException {
		LogginUtils.getInstance().logStart(this.getClass(), "search");
		
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = null;
		
		boolean hasDateSearch = false;
		
		if (!searchInfo.getDateFrom().isEmpty() && !searchInfo.getDateTo().isEmpty()) {
			hasDateSearch = true;
			queryString = SqlUtils.getSQL(SqlUtils.STUDENT_SEARCH_WITH_DATE);
		} else {
			queryString = SqlUtils.getSQL(SqlUtils.STUDENT_SEARCH);
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
		
		return students;
	}
}
