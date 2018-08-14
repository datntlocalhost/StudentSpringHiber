package com.runsystem.datnt.daos.implementations;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.runsystem.datnt.daos.AbstractDao;
import com.runsystem.datnt.daos.interfaces.StudentDao;
import com.runsystem.datnt.entities.Student;

@Repository
@Transactional
public class StudentDaoImpl extends AbstractDao<Integer, Student> implements StudentDao {

	public void insert(Student student) {
		// TODO Auto-generated method stub
		
	}

	public Student selectById(int studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Student selectByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	public Student selectByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Student> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
