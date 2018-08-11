/**
 * CheckLoginAspect class
 * 
 * Define methods to execute check login
 */
package com.runsystem.datnt.aops;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;

public class CheckLoginAspect {
	
	/*
	 * AOP for check login, if user didn't login , will redirect to login page.
	 * 
	 * @param joinPoint
	 */
	public void onCheck(JoinPoint joinPoint) throws Throwable {
		//Get arguments in the method
		Object[] args = joinPoint.getArgs();
		
		HttpServletRequest  request = null;
		HttpServletResponse response = null;
		
		for (Object obj : args) {
			//if obj is a instance of HttpServletRequest object then asignment it to request varible
			if (obj instanceof HttpServletRequest) {
				request  = (HttpServletRequest) obj;
 			}
			
			//if obj is a instance of HttpServletRespone object then asignment it to response varible
			if (obj instanceof HttpServletResponse) {
				response = (HttpServletResponse) obj;
			}
		}
		
		//get session from the request and then check if don't have attribute "user"
		if (request != null && request.getSession().getAttribute("user") == null) {
			response.sendRedirect("/datnt/login");
		}
	}
}