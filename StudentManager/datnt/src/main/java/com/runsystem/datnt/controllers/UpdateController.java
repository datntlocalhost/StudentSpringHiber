package com.runsystem.datnt.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runsystem.datnt.daos.interfaces.StudentDao;
import com.runsystem.datnt.models.StudentModel;
import com.runsystem.datnt.validations.StudentValidator;

@Controller
public class UpdateController {

	@Autowired
	private StudentDao studentDao;
	
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	public @ResponseBody StudentModel updateStudent(@ModelAttribute StudentModel infoUpdate, BindingResult bindingResult,
												HttpServletRequest request, HttpServletResponse response) {
		StudentValidator validator = new StudentValidator();
		validator.validate(infoUpdate, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return null;
		}
				
		return studentDao.update(infoUpdate) ? infoUpdate : null;
	}
}
