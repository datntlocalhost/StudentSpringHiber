package com.runsystem.datnt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runsystem.datnt.models.StudentModel;
import com.runsystem.datnt.services.interfaces.StudentService;
import com.runsystem.datnt.utils.Validation;

@Controller
public class DeleteController {

	@Autowired
	private StudentService studentService;
	
	
	/*
	 * Controler get request info delete student, call delete mothod and return true to
	 * client if delete success, else return false.
	 * 
	 * @param info delete
	 * 
	 * @return true if delete success, else return false.
	 */
	@RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
	public @ResponseBody boolean deleteStudent(@ModelAttribute StudentModel info) {
		if (Validation.validCode(info.getStudentCode())) {
			return  studentService.delete(info.getStudentCode());
		}
		return false;
	}
}
