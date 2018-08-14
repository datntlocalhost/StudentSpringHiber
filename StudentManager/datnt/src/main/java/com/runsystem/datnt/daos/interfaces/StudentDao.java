package com.runsystem.datnt.daos.interfaces;

import java.util.List;

import com.runsystem.datnt.entities.Student;

public interface StudentDao {

	public void          insert(Student student);      
	public void          update(Student student);
	public void          delete(Integer studentId);
	public Student       selectById(int studentId);
	public Student       selectByCode(String code);
	public Student       selectByName(String name);
	public List<Student> selectAll();
}