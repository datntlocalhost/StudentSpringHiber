package com.runsystem.datnt.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	@RequestMapping(value = "/admin/search", method = RequestMethod.POST)
	public @ResponseBody List<StudentListModel> searchStudent(@ModelAttribute SearchStudentModel searchInfo, BindingResult bindingResult,
																HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		SearchValidator validator = new SearchValidator();
		validator.validate(searchInfo, bindingResult);
		
		List<StudentListModel> students = new ArrayList<StudentListModel>();
		
		if (bindingResult.hasErrors()) {
			return students;
		}

		SearchSetup.setup(searchInfo);

		students = studentDao.search(searchInfo);
		
		session.setAttribute("search", searchInfo);		
		
		return students;
	}
}
