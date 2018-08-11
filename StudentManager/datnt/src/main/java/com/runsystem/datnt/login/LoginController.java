package com.runsystem.datnt.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.runsystem.datnt.entities.User;

@Controller
public class LoginController {

	@RequestMapping(value={"/login"}, method = RequestMethod.GET)
	public String loadPage(Model model) {
		model.addAttribute("user", new User());
		return "login.html";
	}
	
	@RequestMapping(value= "/login", method = RequestMethod.POST)
	public String onLogin(@ModelAttribute("user") User user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if (user.getUsername().equals("admin")) {
			session.setAttribute("user", user);
			return "admin.html";
		}
		return "login.html";
	}
	
}
