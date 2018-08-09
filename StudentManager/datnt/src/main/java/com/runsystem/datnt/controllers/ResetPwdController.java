package com.runsystem.datnt.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ResetPwdController {

	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public String loadPage(Model model, HttpServletRequest request) {
		return "reset.html";
	}
}
