package com.runsystem.datnt.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.runsystem.datnt.dtos.TokenDto;
import com.runsystem.datnt.dtos.UserDto;
import com.runsystem.datnt.exceptions.InputInvalidException;
import com.runsystem.datnt.services.TokenService;
import com.runsystem.datnt.services.UserService;
import com.runsystem.datnt.utils.LogginUtils;
import com.runsystem.datnt.validations.UserValidator;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenService tokenService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String pageLoad(Model model, HttpServletRequest request) {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userLogin(@ModelAttribute UserDto userInfo, Model model, HttpServletRequest request, BindingResult bindingResult) {
		LogginUtils.getInstance().logStart(this.getClass(), "userLogin");
		LogginUtils.getInstance().logInfo(this.getClass(), userInfo.toString());
		HttpSession session = request.getSession();
		
		UserValidator validator = new UserValidator();
		validator.validate(userInfo, bindingResult);
		
		if (bindingResult.hasErrors()) {
			LogginUtils.getInstance().logError(this.getClass(), new InputInvalidException("input invalid"));
			return "login";
		}
	
		//Load user info from database
		UserDto user = userService.userLogin(userInfo);
		
		//if correct username and password
		if (user != null) {
			
			//set user info to session
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(15*60);
			
			//create new token
			TokenDto token = tokenService.createToken(user.getUsername());
			
			LogginUtils.getInstance().logEnd(this.getClass(), "userLogin");
			//If create new token success
			if (token != null) {
				//Set token value in session to check for next time
				session.setAttribute("token", token);
				return "redirect:/home";
			} else {
				return "login";
			}
		}
		
		model.addAttribute("error",true);
		LogginUtils.getInstance().logEnd(this.getClass(), "userLogin");
		return "login";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		LogginUtils.getInstance().logStart(this.getClass(), "logoutPage");
		
        //Remove session
		HttpSession session = request.getSession();
        session.invalidate();
        
        LogginUtils.getInstance().logEnd(this.getClass(), "logoutPage");
        return "redirect:/login?logout";
    }
}
