package com.runsystem.datnt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runsystem.datnt.entities.Student;
import com.runsystem.datnt.models.StudentModel;
import com.runsystem.datnt.services.interfaces.StudentService;
import com.runsystem.datnt.validations.StudentValidator;

@Controller
public class CreateStudentController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value = "/admin/create", method = RequestMethod.POST)
	public @ResponseBody List<Student> createStudent(@ModelAttribute StudentModel studentInfo, BindingResult bindingResult) {
		StudentValidator validator = new StudentValidator(true);
		List<Student> students = new ArrayList<Student>();
		
		validator.validate(studentInfo, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return null;
		}
		
		Student student = studentService.insert(studentInfo);
		
		if (student != null) {
			student.getRecord().setStudent(null);
			student.getUser().setStudent(null);
			students.add(student);
		}
		return students;
	}
}
