package com.runsystem.datnt.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runsystem.datnt.daos.interfaces.RoleDao;
import com.runsystem.datnt.daos.interfaces.SchoolDao;
import com.runsystem.datnt.daos.interfaces.StudentDao;
import com.runsystem.datnt.entities.Records;
import com.runsystem.datnt.entities.Role;
import com.runsystem.datnt.entities.School;
import com.runsystem.datnt.entities.Student;
import com.runsystem.datnt.entities.User;
import com.runsystem.datnt.models.SearchStudentModel;
import com.runsystem.datnt.models.StudentModel;
import com.runsystem.datnt.services.interfaces.StudentService;
import com.runsystem.datnt.utils.ConvertObject;
import com.runsystem.datnt.utils.GenerateStudentCode;
import com.runsystem.datnt.utils.Sha256Hash;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private RoleDao    roleDao;
	
	@Autowired
	private SchoolDao  schoolDao;
	
	/*
	 * Setup student's info and insert student to database
	 * 
	 * @param student
	 * 
	 * @return true if insert success, else false
	 */
	public Student insert(StudentModel info) {
		
		String studentCode = GenerateStudentCode.getCode(studentDao.getLastStudentCode());
		String encodePassword = Sha256Hash.hash(info.getPassword());
		Role role = roleDao.selectRoleByName("ROLE_STUDENT");
		
	
		Records records = new Records();
		User    user    = new User();
		School  school = schoolDao.selectByCode(info.getSchool());
		
		//Setup student's record
		records.setSex(info.getSex());
		records.setBirthday(info.getBirthday());
		records.setPhone(info.getPhone());
		records.setEmail(info.getEmail());
		records.setAddress(info.getAddress());
		
		//Set info for student's account
		user.setUsername(studentCode);
		user.setPassword(encodePassword);
		
		user.getRoles().add(role);

		Student student = new Student(0,studentCode, info.getStudentName(), info.getSchoolYear(), school, records, user);
		
		records.setStudent(student);
		user.setStudent(student);
		return studentDao.add(student) > 0 ? student : null;
	}

	/*
	 * Delete student and student's info
	 * 
	 * @param code Student's code
	 * 
	 * @return true if delete success, else faile.
	 */
	public boolean delete(String code) {
		
		Student student = studentDao.selectByCode(code);
		
		if (student != null) {
			studentDao.remove(student);
		}
		return student != null;
	}

	/*
	 * Retrieve list of student from database
	 * 
	 * @return student list
	 */
	public List<Student> list() {
		return studentDao.list();
	}

	/*
	 * Retrieve student from database by student's code
	 * 
	 * @param code Student's code
	 * 
	 * @return Student 
	 */
	public Student selectByCode(String code) {
		return studentDao.selectByCode(code);
	}

	/*
	 * Search student with search info and retrieve list of student from database.
	 * 
	 * @param search info
	 * 
	 * @return list of student info 
	 */
	public List<Student> search(SearchStudentModel searchInfo) {
		return studentDao.search(searchInfo);
	}

	/*
	 * Update student info.
	 * 
	 * @param updateInfo new student info
	 * 
	 * @return student. return null if update failed
	 * */
	public Student updateStudent(StudentModel updateInfo) {
		
		Student student = selectByCode(updateInfo.getStudentCode());
		
		if (student != null) {
			School school = schoolDao.selectByCode(updateInfo.getSchool());
			student.setStudentName(updateInfo.getStudentName());
			student.setStartYear(updateInfo.getSchoolYear());
			student.setSchool(school);
			student.getRecord().setSex(updateInfo.getSex());
			student.getRecord().setBirthday(updateInfo.getBirthday());
			student.getRecord().setPhone(updateInfo.getPhone());
			student.getRecord().setEmail(updateInfo.getEmail());
			student.getRecord().setAddress(updateInfo.getAddress());
			
			studentDao.update(student);
			
			return student;
		}
		return null;
	}
	
	public StudentModel updateRecord(Student student) {
		if (student != null) {
			studentDao.update(student);
			return ConvertObject.objectToModelStudent(student);
		}
		return null;
	}
}
