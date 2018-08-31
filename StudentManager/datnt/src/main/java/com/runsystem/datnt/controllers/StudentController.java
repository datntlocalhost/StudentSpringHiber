package com.runsystem.datnt.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runsystem.datnt.dtos.StudentInfoDto;
import com.runsystem.datnt.dtos.UserDto;
import com.runsystem.datnt.exceptions.InputInvalidException;
import com.runsystem.datnt.exceptions.UpdateException;
import com.runsystem.datnt.models.StudentModel;
import com.runsystem.datnt.services.StudentService;
import com.runsystem.datnt.utils.JsonUtils;
import com.runsystem.datnt.utils.LogginUtils;
import com.runsystem.datnt.validations.StudentValidator;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value = "/student/info", method = RequestMethod.GET)
	public String loadPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		LogginUtils.getInstance().logStart(this.getClass(), "loadPage");
		HttpSession session = request.getSession();
		
		UserDto userDetails = (UserDto) session.getAttribute("user");
		
		if (userDetails != null) {
			String studentCode = userDetails.getUsername();
			
			StudentInfoDto student = studentService.selectByCode(studentCode);
			
			model.addAttribute("student", student);
			
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "loadPage");
		return "studentrecord";
	}
	
	@RequestMapping(value = "/student/info", method = RequestMethod.POST)
	public @ResponseBody String studentUpdate(@RequestBody StudentModel updateInfo, BindingResult bindingResult,
												HttpServletRequest request, HttpServletResponse response) 
														throws InputInvalidException, UpdateException {
		LogginUtils.getInstance().logStart(this.getClass(), "studentUpdate");
		LogginUtils.getInstance().logInfo(this.getClass(), updateInfo.toString());
		
		StudentValidator validator = new StudentValidator();
		validator.validate(updateInfo, bindingResult);
		
		if (bindingResult.hasErrors()) {
			throw new InputInvalidException("Input info update is invalid");
		}
		
		boolean success = true;
		
		try {
			
			studentService.updateStudent(updateInfo);
			
		} catch (UpdateException e) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), e);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "studentUpdate");
		
		if (!success) {
			throw new UpdateException("Could not update student");
		}
		
		String json = JsonUtils.objectToJson(updateInfo);
		
		System.out.println(json);
		
		return json;
	}
}
