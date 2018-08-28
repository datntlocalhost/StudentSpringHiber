package com.runsystem.datnt.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.runsystem.datnt.daos.interfaces.StudentDao;
import com.runsystem.datnt.models.StudentModel;
import com.runsystem.datnt.validations.StudentValidator;

@Controller
public class StudentController {

	@Autowired
	private StudentDao studentDao;
	
	@RequestMapping(value = "/student/info", method = RequestMethod.GET)
	public String loadPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
		
		if (userDetails != null) {
			String studentCode = userDetails.getUsername();
			
			StudentModel student = studentDao.searchByCode(studentCode);
			model.addAttribute("student", student);
			
		}
		return "studentrecord";
	}
	
	@RequestMapping(value = "/student/info", method = RequestMethod.POST)
	public String studentUpdate(@ModelAttribute StudentModel updateInfo, BindingResult bindingResult, Model model,
								HttpServletRequest request, HttpServletResponse response) {
		
		StudentValidator validator = new StudentValidator();
		validator.validate(updateInfo, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("invalid", true);
			model.addAttribute("student", studentDao.searchByCode(updateInfo.getStudentCode()));
			return "studentrecord";
		}
		
		if (studentDao.update(updateInfo)) {
			model.addAttribute("student", updateInfo);
			model.addAttribute("success", true);
			return "studentrecord";
		} 
		model.addAttribute("error", true);
		model.addAttribute("student", studentDao.searchByCode(updateInfo.getStudentCode()));
		return "studentrecord";
	}
}