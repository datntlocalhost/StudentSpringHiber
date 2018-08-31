package com.runsystem.datnt.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runsystem.datnt.exceptions.DeleteException;
import com.runsystem.datnt.models.StudentModel;
import com.runsystem.datnt.services.StudentService;
import com.runsystem.datnt.utils.JsonUtils;
import com.runsystem.datnt.utils.LogginUtils;

@Controller
public class DeleteController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
	public @ResponseBody String deleteStudent(@RequestBody StudentModel deleteInfo, 
												HttpServletRequest request, HttpServletResponse response) throws DeleteException {
		LogginUtils.getInstance().logStart(this.getClass(), "deleteStudent");
		
		boolean success = true;
		
		//convert boolean to json to send to view
		String json = JsonUtils.objectToJson(success);
		
		try {
			
			studentService.deleteStudent(deleteInfo.getStudentCode());
			
		} catch (Exception ex) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), ex);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "deleteStudent");
		
		if (!success) {
			throw new DeleteException("Delete exception : throws exception to Controller Advice");
		}
		
		return json;
	}
}
