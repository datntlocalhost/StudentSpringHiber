package com.runsystem.datnt.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.runsystem.datnt.daos.interfaces.CourseDao;
import com.runsystem.datnt.entities.Course;
import com.runsystem.datnt.services.interfaces.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseDao courseDao;
	
	public void insert(Course course) {
		courseDao.insert(course);
	}

	public void update(Course course) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Integer courseId) {
		// TODO Auto-generated method stub
		
	}

	public Course selectById(int courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Course selectByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	public Course selectByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Course> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}