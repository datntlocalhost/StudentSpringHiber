/**
 * CheckLoginAspect class
 * 
 * Define methods to execute check login
 */
package com.runsystem.datnt.aops;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.springframework.ui.Model;

import com.runsystem.datnt.controllers.LoginController;
import com.runsystem.datnt.dtos.TokenDto;
import com.runsystem.datnt.dtos.UserDto;
import com.runsystem.datnt.services.TokenService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthAOP {
	
	private TokenService tokenService;
	
	/*
	 * AOP for check login, if user didn't login , will redirect to login page.
	 * 
	 * @param joinPoint
	 */
	public void onSet(JoinPoint joinPoint) throws Throwable {
		
		if (joinPoint.getTarget().getClass().equals(LoginController.class)) {
			return;
		}
		
		//Get arguments in the method
		Object[] args = joinPoint.getArgs();
		
		HttpServletRequest  req = null;
		HttpServletResponse res = null;
		HttpSession         session = null;
		Model model = null;
		UserDto user = null;
		TokenDto token = null;
		
		for (Object obj : args) {
			//if obj is a instance of HttpServletRequest object then asignment it to request varible
			if (obj instanceof HttpServletRequest) {
				req = (HttpServletRequest) obj;
 			}
			
			if (obj instanceof HttpServletResponse) {
				res = (HttpServletResponse) obj;
			}
			
			if (obj instanceof Model) {
				model = (Model) obj;
			}
		}
		
		
		session = req.getSession();
		
		//Get user info and token value from session
		user  = (UserDto) session.getAttribute("user");
		token = (TokenDto) session.getAttribute("token");
		
		//if token is not null and user is not null
		if (token != null && user != null) {
			
			//Select last token of this user from database
			TokenDto lastToken = tokenService.getLastToken(user.getUsername());
			
			//if token with the last token get from db is the same and timestampe is not expired then can access
			if (lastToken != null) {
				boolean isSameToken = token.getToken().equals(lastToken.getToken());
				boolean hasExpired = lastToken.getTimestamp().getTime() < System.currentTimeMillis();
				
				if (isSameToken && !hasExpired) {
					if (model != null) {
						model.addAttribute("role", user.getRoles().get(0).getRoleName());
						model.addAttribute("username", user.getUsername());
					}
					return;
				}
			} 
		}
		//return login page if user not login or token has expired.
		res.sendRedirect("/datnt/login?token=false");
	}
}