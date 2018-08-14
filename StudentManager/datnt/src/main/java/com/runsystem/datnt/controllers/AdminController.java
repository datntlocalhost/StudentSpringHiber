package com.runsystem.datnt.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.runsystem.datnt.services.implementations.RoleServiceImpl;

@Controller
public class AdminController {
	
	@Autowired
	RoleServiceImpl roleServiceImpl;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String loadPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println(roleServiceImpl != null);
		System.out.println(roleServiceImpl.selectById(1).getRoleName());
		//System.out.println(roleServiceImpl.selectByName("ROLE_ADMIN").getRoleName());
		
		return "admin.html";
	}
}
