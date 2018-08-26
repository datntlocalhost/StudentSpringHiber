package com.runsystem.datnt.controllers;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runsystem.datnt.entities.PasswordReset;
import com.runsystem.datnt.mails.MailServices;
import com.runsystem.datnt.services.interfaces.PasswordResetService;
import com.runsystem.datnt.utils.Validation;
import com.runsystem.datnt.validations.RequireResetValidator;

@Controller
public class ResetPasswordController {

	@Autowired
	private PasswordResetService resetService;
	
	@Autowired
	private MailServices mail;
	
	/*
	 * 
	 * */
	@RequestMapping(value = "/admin/accreset", method = RequestMethod.GET)
	public String loadPageAdmin(Model model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("listProcessed", resetService.selectProcessed());
		model.addAttribute("listUnprocess", resetService.selectUnprocess());
		return "acceptResetPassword";
	}
	
	@RequestMapping(value = "/sendreset", method = RequestMethod.GET)
	public String loadPageStudent(Model model) {
		return "requireResetPassword";
	}
	
	/*
	 * 
	 * */
	@RequestMapping(value = "/sendreset", method = RequestMethod.POST)
	public String processRequire(@ModelAttribute PasswordReset info, Model model, BindingResult bindingResult) {
		RequireResetValidator validator = new RequireResetValidator();
		validator.validate(info, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", true);
			return "requireResetPassword";
		}
		if ( resetService.insert(info) ) {
			model.addAttribute("success", true);
			return "requireResetPassword";
		}
		model.addAttribute("errors", true);
		return "requireResetPassword";
	}
	
	/*
	 * Process accept reset password of student.
	 * 
	 * @param code
	 * @param param 
	 * 
	 * @return boolean true if process success, else return false.
	 */
	@RequestMapping(value = "/admin/accreset/{studentCode}", method = RequestMethod.GET)
	public @ResponseBody boolean acceptReset(@PathVariable("studentCode") String code, @RequestParam(value = "param", required = true) String param) {
		
		//Check if input is valid
		if (Validation.validCode(code)) {
			if (!Validation.isNullOrEmpty(param)) {
				if (param.equals("accept")) {
					
					//Update and retrieve password reset to database.
					PasswordReset result = resetService.acceptReset(code);
					if (result != null) {
						
						//Send email to student if accept success.
						try {
							mail.send("datntuit@gmail.com", result.getEmail(), "Reset password", "Your password has been reset");
						} catch (MessagingException e) {
							e.printStackTrace();
						}
						return true;
					}
				}
			}
		}
		return false;
	}
}