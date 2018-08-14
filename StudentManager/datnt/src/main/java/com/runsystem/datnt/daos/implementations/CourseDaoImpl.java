package com.runsystem.datnt.daos.implementations;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.runsystem.datnt.daos.AbstractDao;
import com.runsystem.datnt.daos.interfaces.CourseDao;
import com.runsystem.datnt.entities.Course;

@Repository
@Transactional(rollbackFor = Exception.class)
public class CourseDaoImpl extends AbstractDao<Course, Integer> implements CourseDao {

	public void insert(Course school) {
		
	}

	public void update(Course school) {
		
	}

	public void delete(Integer schoolId) {
		
	}

	public Course selectById(int schoolId) {
		return null;
	}

	public Course selectByCode(String code) {
		return null;
	}

	public Course selectByName(String name) {
		return null;
	}

	public List<Course> selectAll() {
		return null;
	}

}
