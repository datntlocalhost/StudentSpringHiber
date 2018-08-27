package com.runsystem.datnt.services.interfaces;

import java.util.List;

import com.runsystem.datnt.entities.Student;
import com.runsystem.datnt.models.SearchStudentModel;
import com.runsystem.datnt.models.StudentModel;

public interface StudentService {

	public Student       insert(StudentModel info);
	public boolean       delete(String code);
	public List<Student> list();
	public Student       selectByCode(String code);
	public List<Student> search(SearchStudentModel searchInfo);
	public Student       updateStudent(StudentModel updateInfo);
	public StudentModel  updateRecord(Student student);
}
