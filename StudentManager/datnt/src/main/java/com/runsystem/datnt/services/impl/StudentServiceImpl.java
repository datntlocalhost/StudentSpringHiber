package com.runsystem.datnt.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runsystem.datnt.daos.RecordDao;
import com.runsystem.datnt.daos.SchoolDao;
import com.runsystem.datnt.daos.StudentDao;
import com.runsystem.datnt.daos.TokenDao;
import com.runsystem.datnt.daos.UserDao;
import com.runsystem.datnt.dtos.RecordDto;
import com.runsystem.datnt.dtos.StudentDto;
import com.runsystem.datnt.dtos.StudentInfoDto;
import com.runsystem.datnt.dtos.UserDto;
import com.runsystem.datnt.exceptions.DeleteException;
import com.runsystem.datnt.exceptions.InsertException;
import com.runsystem.datnt.exceptions.UpdateException;
import com.runsystem.datnt.models.SearchStudentModel;
import com.runsystem.datnt.models.StudentModel;
import com.runsystem.datnt.services.StudentService;
import com.runsystem.datnt.utils.GenerateStudentCode;
import com.runsystem.datnt.utils.LogginUtils;
import com.runsystem.datnt.utils.SearchSetup;
import com.runsystem.datnt.utils.Sha256Hash;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private RecordDao recordDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private TokenDao tokenDao;
	
	@Autowired
	private SchoolDao schoolDao;
	
	
	/*
	 * Create new student
	 * 
	 * @param studentInfo 
	 * 
	 * @return studentmodel if create success, else return null
	 * 
	 * @throws InsertException
	 */
	@Transactional(rollbackFor = InsertException.class)
	public StudentModel createStudent(StudentModel studentInfo) throws InsertException {
		LogginUtils.getInstance().logStart(this.getClass(), "createStudent");
		
		StudentModel studentModel = null;
		StudentDto student = new StudentDto();
		RecordDto  record  = new RecordDto();
		UserDto    user    = new UserDto();
		
		boolean success = true;

		
		try {
			//Get max student id and max user's id from database to calculate the next id.
			int studentId = studentDao.nextPK();
			int userId    = userDao.nextPK();
			int schooolId = schoolDao.getIdByCode(studentInfo.getSchoolCode());
	
			String code = studentDao.getMaxCode();
			
			String nextCode = GenerateStudentCode.getCode(code);
			
			studentInfo.setStudentCode(nextCode);
	
			//setup student info before insert to database.
			student.setStudentId(studentId);
			student.setStudentCode(GenerateStudentCode.getCode(nextCode));
			student.setStudentName(studentInfo.getStudentName());
			student.setSchoolYear(studentInfo.getSchoolYear());
			student.setSchoolId(schooolId);
	
			//Setup student's record before insert to database.
			record.setRecordId(studentId);
			record.setSex(studentInfo.getSex());
			record.setBirthday(studentInfo.getBirthday());
			record.setPhone(studentInfo.getPhone());
			record.setEmail(studentInfo.getEmail());
			record.setAddress(studentInfo.getAddress());
	
			//Setup account info before insert to database.
			user.setUserId(userId);
			user.setUsername(student.getStudentCode());
			user.setPassword(Sha256Hash.hash(studentInfo.getPassword()));
			user.setStudentId(studentId);

			//Call insert method in StudentDao to insert student info.
			studentDao.insert(student);
	
			//Call insert method in RecordDao to insert student record.
			recordDao.insert(record);
	
			//Call insert method in UserDao to insert student's account.
			userDao.insert(user);
	
			//Call insertUserRole method in UserDao to insert role for user.
			userDao.insertUserRole(userId, 2);
			
		} catch (InsertException e) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), e);
		} catch (IOException e) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), e);
		}

		studentModel = studentInfo;
		
		LogginUtils.getInstance().logEnd(this.getClass(), "createStudent");
		
		if (!success) {
			throw new InsertException("InsertException: Could not insert student.");
		}
		
		return studentModel;
	}
	
	/*
	 * Select student by code.
	 * 
	 * @param student's code
	 * 
	 * @return student info
	 */
	@Transactional
	public StudentInfoDto selectByCode(String code) {
		LogginUtils.getInstance().logStart(this.getClass(), "selectByCode");
		StudentInfoDto student = null;
		
		try {
			
			student = studentDao.selectStudentByCode(code);
			
		} catch (IOException e) {
			LogginUtils.getInstance().logError(this.getClass(), e);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "selectByCode");
		return student;
	}

	/*
	 * Delete student info.
	 *    + delete student
	 *    + delete record
	 *    + delete user
	 *    + delete all of user's token
	 *    
	 * @param studentCode
	 * 
	 * @return true if delete success, else return false.
	 * 
	 * @throws DeleteException
	 */
	@Transactional(rollbackFor = DeleteException.class)
	public void deleteStudent(String studentCode) throws DeleteException {
		LogginUtils.getInstance().logStart(this.getClass(), "delete");

		boolean success = true;
		
		try {
			Integer studentId = studentDao.getIdByCode(studentCode);
			UserDto user	  = userDao.selectByStudentId(studentId);
			
			//Delete student info
			studentDao.delete(studentId);
			
			//Delete student's record
			recordDao.delete(studentId);
			
			//Delete student's account
			userDao.delete(studentId);
			
			//delete user token
			tokenDao.delete(user.getUsername());
					
		} catch (IOException e) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), e);
		} catch (DeleteException e) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), e);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "delete");
		
		if (!success) {
			throw new DeleteException("DeleteException: Could not delete student");
		}

	}

	/*
	 * Get max current code from student table in database.
	 * 
	 * @return max current code.
	 */
	@Transactional
	public String getMaxCurrentCode() {
		LogginUtils.getInstance().logStart(this.getClass(), "getMaxCurrentCode");
		String code = null;
		
		try {
			code = studentDao.getMaxCode();
		} catch (IOException e) {
			LogginUtils.getInstance().logError(this.getClass(), e);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "getMaxCurrentCode");
		return code;
	}

	/*
	 * Get list of student
	 * 
	 * @return list of student info dto
	 */
	@Transactional
	public List<StudentInfoDto> getStudentList() {
		LogginUtils.getInstance().logStart(this.getClass(), "getStudentList");
		
		List<StudentInfoDto> students = new ArrayList<StudentInfoDto>();
		
		try {
			students = studentDao.list();
			
		} catch (IOException e) {
			LogginUtils.getInstance().logError(this.getClass(), e);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "getStudentList");
		return students;
	}
	
	/*
	 * Update student info.
	 * 
	 * @param studentinfo
	 * 
	 * @throws UpdateException
	 */
	@Transactional(rollbackFor = UpdateException.class)
	public void updateStudent(StudentModel studentInfo) throws UpdateException {
		LogginUtils.getInstance().logStart(this.getClass(), "updateStudent");
		
		StudentDto student = new StudentDto();
		RecordDto record = new RecordDto();
		
		boolean success = true;
		
		try {
			
			//Get student's id by student's code
			Integer studentId = studentDao.getIdByCode(studentInfo.getStudentCode());
			
			//Get school id by school's code
			Integer schoolId = schoolDao.getIdByCode(studentInfo.getSchoolCode());
			
			//Setup student info with newest info before update
			student.setStudentId(studentId);
			student.setStudentCode(studentInfo.getStudentCode());
			student.setStudentName(studentInfo.getStudentName());
			student.setSchoolYear(studentInfo.getSchoolYear());
			student.setSchoolId(schoolId);
			
			//Setup record info before update
			record.setRecordId(studentId);
			record.setSex(studentInfo.getSex());
			record.setBirthday(studentInfo.getBirthday());
			record.setPhone(studentInfo.getPhone());
			record.setEmail(studentInfo.getEmail());
			record.setAddress(studentInfo.getAddress());
			
			//call update method to update student's info.
			studentDao.update(student);
			
			//call update method to update student's record.
			recordDao.update(record);
			
		} catch (IOException e) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), e);
		} catch (UpdateException e) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), e);
		}

		LogginUtils.getInstance().logEnd(this.getClass(), "updateStudent");
		
		if (!success) {
			throw new UpdateException("UpdateException: Could not update student");
		}
	}

	/*
	 * Search student.
	 * 
	 * @param searchInfo
	 * 
	 * @return list of student info
	 */
	@Transactional
	public List<StudentInfoDto> searchStudent(SearchStudentModel searchInfo) {
		LogginUtils.getInstance().logStart(this.getClass(), "searchStudent");
		
		List<StudentInfoDto> students = new ArrayList<StudentInfoDto>();
		
		//Setup search info before search
		SearchSetup.setup(searchInfo);
		
		try {
			
			students = studentDao.search(searchInfo);
			
		} catch (IOException e) {
			LogginUtils.getInstance().logError(this.getClass(), e);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "searchStudent");
		return students;
	}
}



















