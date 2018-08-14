package com.runsystem.datnt.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.runsystem.datnt.entities.Student;

@Controller
public class CreateStudentController {

	/*
	 * Load create page and set attribute for view to render
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * 
	 * @return create page
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String loadPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("userRules", "ADMIN");
		model.addAttribute("student", new Student());
		return "create.html";
	}
}
