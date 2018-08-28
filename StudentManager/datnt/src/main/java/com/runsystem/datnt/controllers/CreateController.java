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

import com.runsystem.datnt.daos.interfaces.SchoolDao;
import com.runsystem.datnt.daos.interfaces.StudentDao;
import com.runsystem.datnt.models.StudentModel;
import com.runsystem.datnt.utils.GenerateStudentCode;
import com.runsystem.datnt.utils.Sha256Hash;
import com.runsystem.datnt.validations.StudentValidator;

@Controller
public class CreateController {
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private SchoolDao schoolDao;

	/*
	 * Get POST request contain create info and call insert method to insert new student into database.
	 * 
	 * @param studentInfo
	 * @param bindingResult
	 * @param request
	 * @param response
	 * 
	 * @return null if input is invalid or insert failed, return student info if insert is success.
	 */
	@RequestMapping(value = "/admin/create", method = RequestMethod.POST)
	public @ResponseBody StudentModel createStudent(@ModelAttribute StudentModel studentInfo, BindingResult bindingResult,
													HttpServletRequest request, HttpServletResponse response) {
		StudentValidator validator = new StudentValidator();
		validator.validate(studentInfo, bindingResult);
		if (bindingResult.hasErrors()) {
			return null;
		}
		//Encode password with SHA256 algorithm before insert to database
		String studentCode = GenerateStudentCode.getCode(studentDao.getMaxCode());
		studentInfo.setPassword(Sha256Hash.hash(studentInfo.getPassword()));
		
		//Set student's code
		studentInfo.setStudentCode(studentCode);
		
		boolean success = studentDao.insert(studentInfo);
		
		studentInfo.setSchool(schoolDao.getCode(studentInfo.getSchool()));
		
		return success ? studentInfo : null;
	}
}
