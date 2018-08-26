package com.runsystem.datnt.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@Autowired
	private Test test;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody boolean loadPage(Model model) {
		
		UserTest user = test.getUserById(46);
		
		if (user != null) {
			System.out.println(user.toString());
		}
		
		return true;
	}
}