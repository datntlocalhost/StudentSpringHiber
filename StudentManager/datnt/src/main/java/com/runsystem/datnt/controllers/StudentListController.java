package com.runsystem.datnt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.runsystem.datnt.entities.Student;
import com.runsystem.datnt.models.SearchStudentModel;
import com.runsystem.datnt.services.interfaces.SchoolService;
import com.runsystem.datnt.services.interfaces.StudentService;

@Controller
public class StudentListController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private SchoolService schoolService;
	
	/*
	 * Load page student list 
	 * 
	 * @param model
	 * 
	 * @return page student list
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public String loadPage(Model model) {
		model.addAttribute("schools", schoolService.selectAll());
		model.addAttribute("studentList", studentService.list());
		return "studentlist";
	}
	
	
	/*
	 * Process get request search info from client, retrieve data from database and return data to client.
	 * 
	 * @param searchmodel
	 * @param bindingResult to check validation input
	 * 
	 * @return list of student
	 */
	@PostMapping(value = "/admin/search")
	public @ResponseBody List<Student> searchStudent(@ModelAttribute SearchStudentModel searchModel, BindingResult bindingResult) throws JsonProcessingException {
		
		//retrieve list of student from database
		List<Student> students = studentService.search(searchModel);
	
		for (Student student : students) {
			student.getRecord().setStudent(null);
			student.setUser(null);
		}
		
		return students ;
	}
}
