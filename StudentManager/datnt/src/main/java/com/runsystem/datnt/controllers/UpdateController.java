package com.runsystem.datnt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runsystem.datnt.models.StudentModel;
import com.runsystem.datnt.services.interfaces.StudentService;
import com.runsystem.datnt.validations.StudentValidator;

@Controller
public class UpdateController {
	
	@Autowired
	private StudentService studentService;

	/*
	 * Controller get request contain update info of student, check info is valid, if info is invalid,
	 * return null, else call updateStudent method to update.
	 * 
	 * @param updateInfo
	 * @param bindingResult to check valid info/
	 * 
	 * @return studentModel
	 */
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	public @ResponseBody StudentModel updateStudent(@ModelAttribute StudentModel updateInfo, BindingResult bindingResult) {
		StudentValidator validator = new StudentValidator();
		validator.validate(updateInfo, bindingResult);
		
		//check if info is invalid, thhen return null
		if (bindingResult.hasErrors()) {
			return null;
		}
		
		//Call updateStudent method to update
		if (studentService.updateStudent(updateInfo) != null) {
			return updateInfo;
		}
		return null;
	}
}
