package com.runsystem.datnt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RecordController {

	@RequestMapping(value = "/record", method = RequestMethod.GET)
	public String loadPage(Model model) {
		
		return "record.html";
	}
}
