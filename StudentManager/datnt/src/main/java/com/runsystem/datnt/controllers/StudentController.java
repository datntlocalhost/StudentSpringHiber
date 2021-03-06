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
import com.runsystem.datnt.exceptions.AuthException;
import com.runsystem.datnt.exceptions.InputInvalidException;
import com.runsystem.datnt.exceptions.UpdateException;
import com.runsystem.datnt.models.ResponePackage;
import com.runsystem.datnt.models.StudentModel;
import com.runsystem.datnt.services.StudentService;
import com.runsystem.datnt.services.TokenService;
import com.runsystem.datnt.utils.HeaderPackage;
import com.runsystem.datnt.utils.JsonUtils;
import com.runsystem.datnt.utils.LogginUtils;
import com.runsystem.datnt.validations.StudentValidator;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TokenService tokenService;
	
	/*
	 * loadPage
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * 
	 * @return student info page 
	 */
	@RequestMapping(value = "/student/info", method = RequestMethod.GET)
	public String loadPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		LogginUtils.getInstance().logStart(this.getClass(), "loadPage");
		HttpSession session = request.getSession();
		
		//Check token is valid, if token has expired then return to login page.
		try {
			tokenService.checkValidToken(session);
		} catch (AuthException e) {
			LogginUtils.getInstance().logContent(this.getClass(), "Token has expired");
			LogginUtils.getInstance().logEnd(this.getClass(), "loadPage");
			return "redirect:/login?token=false";
		}
		
		//Get user info from session
		UserDto userDetails = (UserDto) session.getAttribute("user");
		
		//Add username and role to model attribute
		model.addAttribute("username", userDetails.getUsername());
		model.addAttribute("role", userDetails.getRoles().get(0).getRoleName());
		
		String studentCode = userDetails.getUsername();
		
		//Select student info from db to render student info on view
		StudentInfoDto student = studentService.selectByCode(studentCode);
		student.setDayString(student.getBirthday().toString());
		model.addAttribute("student", student);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "loadPage");
		return "studentrecord";
	}
	
	/*
	 * get info student update from client and process bussiness login and return json result to view.
	 * 
	 * @param updateInfo The info get from request client.
	 * @param bindingResult The object to content errors when check valid input.
	 * @param request
	 * @param response
	 * 
	 * @return json content student update info
	 * 
	 * @throws InputInvalidException The exception throws when input from client is invalid.
	 * @throws UpdateException The exception throws when update has error.
	 * @throws AuthException The exception throws when user's token has expired.
	 */
	@RequestMapping(value = "/student/info", method = RequestMethod.POST)
	public @ResponseBody String studentUpdate(@RequestBody StudentModel updateInfo, BindingResult bindingResult,
												HttpServletRequest request, HttpServletResponse response) 
														throws InputInvalidException, UpdateException, AuthException {
		LogginUtils.getInstance().logStart(this.getClass(), "studentUpdate");
		
		//Check token is valid, if token has expired then throws AuthException
		tokenService.checkValidToken(request.getSession());
		
		//Log input from client
		LogginUtils.getInstance().logInputFromView(this.getClass(),  request, updateInfo.toString());
		
		//Check input
		StudentValidator validator = new StudentValidator();
		validator.validate(updateInfo, bindingResult);
		
		if (bindingResult.hasErrors()) {
			throw new InputInvalidException("Input info update is invalid");
		}
		
		//This varible to make sure that update process is successfully or not.
		boolean success = true;
		
		try {
			//Call updateStudent method to update info student
			studentService.updateStudent(updateInfo);
			
		} catch (UpdateException e) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), e);
		}
		
		//Create responsePackage with header is STUDENT_UPDATE
		ResponePackage<StudentModel> responePackage = new ResponePackage<StudentModel>(HeaderPackage.STUDENT_UPDATE);
		responePackage.getData().add(updateInfo);
		
		//Convert responsePackage object to json string.
		String json = JsonUtils.objectToJson(responePackage);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "studentUpdate");
		
		//If update process is not successfully then throws UpdateException to Controller Advice
		if (!success) {
			throw new UpdateException("Could not update student");
		}
		
		return json;
	}
}
