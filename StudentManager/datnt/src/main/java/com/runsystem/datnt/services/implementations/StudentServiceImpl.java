package com.runsystem.datnt.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.runsystem.datnt.daos.interfaces.StudentDao;
import com.runsystem.datnt.entities.Student;
import com.runsystem.datnt.services.interfaces.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDao studentDao;

	public void insert(Student student) {
		// TODO Auto-generated method stub
		
	}

	public void update(Student student) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Integer studentId) {
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
