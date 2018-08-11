package com.runsystem.datnt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CreateStudentController {

	/*
	 * Load create page and set attribute for view to render
	 * 
	 * @param model
	 * @param request
	 * 
	 * @return create page
	 * */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String loadPage(Model model) {
		model.addAttribute("userRules", "ADMIN");
		return "create.html";
	}
}
