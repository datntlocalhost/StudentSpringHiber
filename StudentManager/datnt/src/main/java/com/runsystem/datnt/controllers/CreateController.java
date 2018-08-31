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
import com.runsystem.datnt.exceptions.InsertException;
import com.runsystem.datnt.models.StudentModel;
import com.runsystem.datnt.services.StudentService;
import com.runsystem.datnt.utils.JsonUtils;
import com.runsystem.datnt.utils.LogginUtils;
import com.runsystem.datnt.validations.StudentValidator;

@Controller
public class CreateController {
	
	@Autowired
	private StudentService studentService;
	

	/*
	 * Get POST request contain create info and call insert method to insert new student into database.
	 * 
	 * @param model student's info
	 * @param bindingResult
	 * @param request
	 * @param response
	 * 
	 * @return student info if insert is success, else forward to ExceptionHandlerController
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/create", method = RequestMethod.POST)
	public @ResponseBody String createStudent (@RequestBody StudentModel model, BindingResult bindingResult,
												HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LogginUtils.getInstance().logStart(this.getClass(), "createStudent");
		LogginUtils.getInstance().logInfo(this.getClass(), model.toString());
		
		//Check input is valid
		StudentValidator validator = new StudentValidator(true);
		
		//Call validate method to check input, if has any field in model object is invalid, set has errors to bindingResult.
		validator.validate(model, bindingResult);

		//If input is invalid, log and throw InputInvalidException to ExceptionHandlerController.
		if (bindingResult.hasErrors()) {
			
			LogginUtils.getInstance().logEnd(this.getClass(), "createStudent");
			throw new InputInvalidException("Input from client is invalid format");
		}		
		
		StudentModel student = null;
		
		try {
			//Call createStudent method in StudentService to create new student.
			student = studentService.createStudent(model);
			
		} catch (Exception ex) {
			LogginUtils.getInstance().logEnd(this.getClass(), "createStudent");
			throw new InsertException("Could not create new student");
		}
		
		String jsonReturn = JsonUtils.objectToJson(student);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "createStudent");
		return jsonReturn;
	}
}
