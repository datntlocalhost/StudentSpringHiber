package com.runsystem.datnt.controllers;

import java.util.ArrayList;
import java.util.List;

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
import com.runsystem.datnt.models.SearchStudentModel;
import com.runsystem.datnt.models.StudentListModel;
import com.runsystem.datnt.utils.SearchSetup;
import com.runsystem.datnt.validations.SearchValidator;

@Controller
public class SearchController {
	
	@Autowired
	private StudentDao studentDao;

	/*
	 * This controller get POST request contain search info from client. select from database and return result
	 * for client to render.
	 * 
	 * @param searchInfo
	 * @param binding result to check input is valid.
	 * @param request
	 * @param response
	 * 
	 * @return list of Student List Model object
	 */
	@RequestMapping(value = "/admin/search", method = RequestMethod.POST)
	public @ResponseBody List<StudentListModel> searchStudent(@ModelAttribute SearchStudentModel searchInfo, BindingResult bindingResult,
																HttpServletRequest request, HttpServletResponse response) {
		//HttpSession session = request.getSession();
		
		//init search validator and check input
		SearchValidator validator = new SearchValidator();
		validator.validate(searchInfo, bindingResult);
		
		List<StudentListModel> students = new ArrayList<StudentListModel>();
		
		//if input is invalid then return empty list student info to client.
		if (bindingResult.hasErrors()) {
			return students;
		}

		//setup search info before search from database
		SearchSetup.setup(searchInfo);

		//search from database.
		students = studentDao.search(searchInfo);
		
		//session.setAttribute("search", searchInfo);
		
		return students;
	}
}