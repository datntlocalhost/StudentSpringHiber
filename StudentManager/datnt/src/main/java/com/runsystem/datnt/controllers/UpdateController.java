package com.runsystem.datnt.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runsystem.datnt.exceptions.InputInvalidException;
import com.runsystem.datnt.exceptions.UpdateException;
import com.runsystem.datnt.models.StudentModel;
import com.runsystem.datnt.services.StudentService;
import com.runsystem.datnt.utils.JsonUtils;
import com.runsystem.datnt.utils.LogginUtils;
import com.runsystem.datnt.validations.StudentValidator;

@Controller
public class UpdateController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	public @ResponseBody String updateStudent(@RequestBody StudentModel infoUpdate, BindingResult bindingResult,
												HttpServletRequest request, HttpServletResponse response) throws InputInvalidException, UpdateException {
		LogginUtils.getInstance().logStart(this.getClass(), "updateStudent");
		
		//Log info of infoUpdate was send by client.
		LogginUtils.getInstance().logInfo(this.getClass(), infoUpdate.toString());
		
		StudentValidator validator = new StudentValidator();
		
		//call validate method in StudentValidator to check input, if have any feild of infoUpdate is invalid then set hasError to 
		//bindingResult
		validator.validate(infoUpdate, bindingResult);
		
		//If input invalid then throws InputInvalidException to Controller Advice
		if (bindingResult.hasErrors()) {
			LogginUtils.getInstance().logEnd(this.getClass(), "createStudent");
			throw new InputInvalidException("Input from client is invalid format");
		}
		
		try {
			//update student
			studentService.updateStudent(infoUpdate);
			
		} catch (Exception ex) {
			LogginUtils.getInstance().logEnd(this.getClass(), "updateStudent");
			throw new UpdateException("Could not update student");
		}
		
		String json = JsonUtils.objectToJson(infoUpdate);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "updateStudent");
		
		return json;
	}
}
