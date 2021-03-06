package com.runsystem.datnt.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runsystem.datnt.exceptions.AuthException;
import com.runsystem.datnt.exceptions.DeleteException;
import com.runsystem.datnt.models.ResponePackage;
import com.runsystem.datnt.models.StudentModel;
import com.runsystem.datnt.services.StudentService;
import com.runsystem.datnt.services.TokenService;
import com.runsystem.datnt.utils.HeaderPackage;
import com.runsystem.datnt.utils.JsonUtils;
import com.runsystem.datnt.utils.LogginUtils;

@Controller
public class DeleteController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
	public @ResponseBody String deleteStudent(@RequestBody StudentModel deleteInfo, 
												HttpServletRequest request, HttpServletResponse response) throws DeleteException, AuthException {
		LogginUtils.getInstance().logStart(this.getClass(), "deleteStudent");
		
		//Check token is valid, if token is invalid then throws AuthException
		tokenService.checkValidToken(request.getSession());
		
		boolean success = true;
		
		try {
			
			studentService.deleteStudent(deleteInfo.getStudentCode());
			
		} catch (Exception ex) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		//Create new response package
		@SuppressWarnings("rawtypes")
		ResponePackage responePackage = new ResponePackage(HeaderPackage.DELETE_SUCCESS);
		
		//convert response package to json
		String jsonReturn = JsonUtils.objectToJson(responePackage);
		
		LogginUtils.getInstance().logEnd(this.getClass(), "deleteStudent");
		
		if (!success) {
			throw new DeleteException("Delete exception : throws exception to Controller Advice");
		}
		
		return jsonReturn;
	}
}
