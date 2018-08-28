package com.runsystem.datnt.daos.implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.runsystem.datnt.daos.interfaces.StudentDao;
import com.runsystem.datnt.entities.Records;
import com.runsystem.datnt.entities.School;
import com.runsystem.datnt.entities.Student;
import com.runsystem.datnt.models.SearchStudentModel;
import com.runsystem.datnt.models.StudentListModel;
import com.runsystem.datnt.models.StudentModel;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	/*
	 * Insert student's info into databale.
	 * 
	 * Insert student's records 
	 * Insert student's account
	 * 
	 * @param studentInfo
	 * 
	 * @return true if all query is success, else return false.
	 */
	public boolean insert(StudentModel studentInfo) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		boolean success = false;
		
		String maxIdStudent  = "SELECT MAX(student_id) FROM STUDENT";
		String maxIdUser     = "SELECT MAX(user_id) FROM USER";
		String studentQuery  = "INSERT INTO STUDENT(student_id, student_code, student_name, student_startyear, school_id) " +
							    "VALUES(:id, :code, :name, :schoolyear, :schoolid)";
		String recordQuery   = "INSERT INTO RECORDS(student_id, records_sex, records_birthday, records_phone, records_email, records_address) " +
						        "VALUES(:id, :sex, :birthday, :phone, :email, :address)";
		String userQuery     = "INSERT INTO USER(user_id, user_username, user_password, student_id) " + 
						      	"VALUES(:id, :username, :password, :studentid)";
		String userRoleQuery = "INSERT INTO USER_ROLE(user_id, role_id) " +
						      	"VALUES(:userid, :roleid)";
		
		try {
			transaction = session.beginTransaction();
			
			//Get max student's id in database
			Integer maxStudent = (Integer) session.createSQLQuery(maxIdStudent).uniqueResult();
			//Get max user's id in database
			Integer maxUser = (Integer) session.createSQLQuery(maxIdUser).uniqueResult();
			
			if (maxStudent == null) {
				maxStudent = 1;
			}
			if (maxUser == null) {
				maxUser = 1;
			}
			
			//process insert student's info
			session.createSQLQuery(studentQuery)
				.setParameter("id", maxStudent + 1)
				.setParameter("code", studentInfo.getStudentCode())
				.setParameter("name", studentInfo.getStudentName())
				.setParameter("schoolyear", studentInfo.getSchoolYear())
				.setParameter("schoolid", studentInfo.getSchool())
				.executeUpdate();

			//process insert student's records
			session.createSQLQuery(recordQuery)
				.setParameter("id", maxStudent + 1)
				.setParameter("sex", studentInfo.getSex())
				.setParameter("birthday", studentInfo.getBirthday())
				.setParameter("phone", studentInfo.getPhone())
				.setParameter("email", studentInfo.getEmail())
				.setParameter("address", studentInfo.getAddress())
				.executeUpdate();
			
			//process insert student's account
			session.createSQLQuery(userQuery)
				.setParameter("id", maxUser + 1)
				.setParameter("username", studentInfo.getStudentCode())
				.setParameter("password", studentInfo.getPassword())
				.setParameter("studentid", maxStudent + 1)
				.executeUpdate();

			//process insert info of account (role)
			session.createSQLQuery(userRoleQuery)
				.setParameter("userid", maxUser + 1)
				.setParameter("roleid", 2)
				.executeUpdate();
			transaction.commit();
			success = true;
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			success = false;
			ex.printStackTrace();
		} finally {
			session.close();
		}
		
		return success;
	}
	
	/*
	 * Get list of student from database
	 * 
	 * @return list of studentlistmodel
	 */
	@SuppressWarnings("unchecked")
	public List<StudentListModel> list() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<StudentListModel> studentList = new ArrayList<StudentListModel>();
		
		
		String queryString = "select st.*, r.*, sc.* from STUDENT st, RECORDS r, SCHOOL sc WHERE st.student_id = r.student_id AND st.school_id=sc.school_id";
		try {
			transaction = session.beginTransaction();
			Query<Object[]> query = session.createSQLQuery(queryString).addEntity(Student.class).addEntity(Records.class).addEntity(School.class);
			for (Object[] result : query.list()) {
				studentList.add(new StudentListModel((Student) result[0], (Records) result[1], (School) result[2]));
			}
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return studentList;
	}
	
	/*
	 * Get max code string of student table
	 * 
	 * @return code string
	 */
	@SuppressWarnings("unchecked")
	public String getMaxCode() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		String maxCode = null;
		
		String queryString = "SELECT MAX(student_code) FROM STUDENT";
		
		try {
			transaction = session.beginTransaction();
			Query<String> query = session.createSQLQuery(queryString);
			maxCode = query.uniqueResult();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
		
		return maxCode;
	}

	/*
	 * Search student
	 * 
	 * @param searchInfo
	 * 
	 * @return List of Student
	 */
	@SuppressWarnings("unchecked")
	public List<StudentListModel> search(SearchStudentModel searchInfo) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<StudentListModel> students = new ArrayList<StudentListModel>();
		
		String queryString = "SELECT st.*, r.*, sc.* " + 
							 "FROM STUDENT st, RECORDS r, SCHOOL sc " +
							 "WHERE st.student_id = r.student_id " + 
							 	"AND st.school_id = sc.school_id " +
							 	"AND st.student_code like :code " +
							 	"AND st.student_name like :name " +
							 	"AND r.records_sex like :sex " +
							 	"AND sc.school_id like :schoolid ";
		
		if (!searchInfo.getDateFrom().isEmpty() && !searchInfo.getDateTo().isEmpty()) {
			queryString += "AND r.records_birthday >= :datefrom AND r.records_birthday <= :dateto ";
		}
		
		try {
			transaction = session.beginTransaction();
			
			Query<Object[]> query = session
										.createSQLQuery(queryString)
											.addEntity(Student.class)
											.addEntity(Records.class)
											.addEntity(School.class);
			query.setParameter("code", searchInfo.getStudentCode())
				 .setParameter("name", searchInfo.getStudentName())
				 .setParameter("sex", searchInfo.getSex())
				 .setParameter("schoolid", searchInfo.getSchool());
			
			if (!searchInfo.getDateFrom().isEmpty() && !searchInfo.getDateTo().isEmpty()) {
				query.setParameter("datefrom", searchInfo.getDateFrom()).setParameter("dateto", searchInfo.getDateTo());
			}
			
			
			for (Object[] result : query.list()) {
				students.add(new StudentListModel((Student) result[0], (Records) result[1], (School) result[2]));
			}
			
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
		
		return students;
	}

	/*
	 * Update student
	 * 
	 * @param studentInfo
	 * 
	 * @return boolean true if update success, else return false.
	 */
	public boolean update(StudentModel studentInfo) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		boolean success = false;
		
		String getSchoolQuery = "SELECT sc.school_id FROM SCHOOL sc WHERE sc.school_code = :code";
		String queryString = "UPDATE STUDENT st, RECORDS r " + 
							 "SET st.student_name = :name," +
							 	" st.student_startyear = :schoolyear," + 
							 	" st.school_id = :schoolid," + 
							 	" r.records_sex = :sex," + 
							 	" r.records_birthday = :birthday," +
							 	" r.records_phone = :phone," +
							 	" r.records_email = :email," +
							 	" r.records_address = :address " + 
							 "WHERE st.student_id = r.student_id AND st.student_code = :code";
		
		try {
			transaction = session.beginTransaction();
			
			int schoolId = (Integer) session.createSQLQuery(getSchoolQuery).setParameter("code", studentInfo.getSchool()).uniqueResult();
			
			success = session.createSQLQuery(queryString)
						.setParameter("name", studentInfo.getStudentName())
						.setParameter("schoolyear", studentInfo.getSchoolYear())
						.setParameter("schoolid", schoolId)
						.setParameter("sex", studentInfo.getSex())
						.setParameter("birthday", studentInfo.getBirthday())
						.setParameter("phone", studentInfo.getPhone())
						.setParameter("email", studentInfo.getEmail())
						.setParameter("address", studentInfo.getAddress())
						.setParameter("code", studentInfo.getStudentCode())
						.executeUpdate() > 0;
			
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			success = false;
			ex.printStackTrace();
		} finally {
			session.close();
		}
		
		return success;
	}

	/*
	 * Delete student by student's code
	 * 
	 * @param student's code
	 * 
	 * @return boolean true if delete success, else return false.
	 */
	public boolean delete(String studentCode) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		boolean success = false;
		
		String getStudentId = "SELECT st.student_id FROM STUDENT st WHERE st.student_code = :code";
		
		String query = "DELETE st, r, u " +
				 	   "FROM STUDENT st, RECORDS r, USER u " +
				 	   "WHERE st.student_id = r.student_id AND st.student_id = u.student_id AND st.student_id = :studentid";
		
		try {
			transaction = session.beginTransaction();
			
			int studentId = (Integer) session.createSQLQuery(getStudentId).setParameter("code", studentCode).uniqueResult();
			
			success = session.createSQLQuery(query).setParameter("studentid", studentId).executeUpdate() > 0;
			
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			success = false;
			ex.printStackTrace();
		} finally {
			session.close();
		}
	
		return success;
	}

	/*
	 * Search student's info by student's code, and convert result to StudentModel object.
	 * 
	 * @param code
	 * 
	 * @return student model
	 */
	public StudentModel searchByCode(String code) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		StudentModel studentInfo = new StudentModel();
		
		String queryString =  "SELECT st.*, r.*, sc.* " + 
				 			  "FROM STUDENT st, RECORDS r, SCHOOL sc " +
				 			  "WHERE st.student_id = r.student_id " + 
				 			  	"AND st.school_id = sc.school_id " +
				 			  	"AND st.student_code = :code";
		
		try {//lombok pool
			transaction = session.beginTransaction();
			
			Object[] obj = (Object[]) session.createSQLQuery(queryString).addEntity(Student.class).addEntity(Records.class).addEntity(School.class)
					.setParameter("code", code).getSingleResult();
			
			if (obj.length >= 3) {
				Student student = (Student) obj[0];
				Records record  = (Records) obj[1];
				School  school  = (School) obj[2];
				studentInfo.setStudentCode(student.getStudentCode());
				studentInfo.setStudentName(student.getStudentName());
				studentInfo.setBirthday(record.getBirthday());
				studentInfo.setSex(record.getSex());
				studentInfo.setSchool(school.getSchoolCode());
				studentInfo.setSchoolYear(student.getStartYear());
				studentInfo.setPhone(record.getPhone());
				studentInfo.setEmail(record.getEmail());
				studentInfo.setAddress(record.getAddress());
			}
			
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
		
		return studentInfo;
	}
	
	

}
