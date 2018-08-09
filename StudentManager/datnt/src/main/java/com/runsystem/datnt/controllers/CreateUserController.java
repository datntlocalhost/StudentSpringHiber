package com.runsystem.datnt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CreateUserController {

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String loadPage(Model model) {
		return "user.html";
	}
}
