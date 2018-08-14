package com.runsystem.datnt.daos.interfaces;

import java.util.List;

import com.runsystem.datnt.entities.Course;

public interface CourseDao {

	public void         insert(Course course);       
	public void         update(Course course);
	public void         delete(Integer courseId);
	public Course       selectById(int courseId);
	public Course       selectByCode(String code);
	public Course       selectByName(String name);
	public List<Course> selectAll();
}
