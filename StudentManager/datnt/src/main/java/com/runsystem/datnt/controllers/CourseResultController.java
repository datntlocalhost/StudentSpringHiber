package com.runsystem.datnt.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CourseResultController {
	
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String pageLoad(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "result.html";
	}
}
