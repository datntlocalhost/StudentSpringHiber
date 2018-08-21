package com.runsystem.datnt.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String loadPage(Model model) {
		//model.addAttribute("user", getPrincipal());
		//model.addAttribute("role", getRole() );
		return "home";
	}
	
	

	
	private String getRole() {
        String role     = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            role = ((UserDetails)principal).getAuthorities().iterator().next().getAuthority();
        } else {
        	role = principal.toString();
        }
        return role;
	}
}
